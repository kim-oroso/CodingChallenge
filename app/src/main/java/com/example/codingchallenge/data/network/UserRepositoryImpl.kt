package com.example.codingchallenge.data.network

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.codingchallenge.data.model.User
import com.example.codingchallenge.data.model.UserDetail
import com.example.codingchallenge.domain.repository.UserRepository
import com.example.codingchallenge.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserService
) : UserRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getUsers(): Flow<Result<List<User>>> = flow {
        emit(Result.Loading())
        try {
            val users = api.getUsers()
            emit(Result.Success(users))
        } catch (e: HttpException) {
            emit(Result.Error("HTTP error: ${e.message}"))
        } catch (e: IOException) {
            emit(Result.Error("Network error: ${e.message}"))
        } catch (e: Exception) {
            emit(Result.Error("Unknown error: ${e.message}"))
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getUserDetails(userId: String): Flow<Result<UserDetail>> = flow {
        emit(Result.Loading())
        try {
            val user = api.getUserDetails(userId)
            emit(Result.Success(user))
        } catch (e: HttpException) {
            emit(Result.Error("HTTP error: ${e.message}"))
        } catch (e: IOException) {
            emit(Result.Error("Network error: ${e.message}"))
        } catch (e: Exception) {
            emit(Result.Error("Unknown error: ${e.message}"))
        }
    }

}