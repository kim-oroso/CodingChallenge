package com.example.codingchallenge.domain.repository

import com.example.codingchallenge.data.model.User
import com.example.codingchallenge.data.model.UserDetail
import com.example.codingchallenge.utils.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<Result<List<User>>>
    fun getUserDetails(userId: String): Flow<Result<UserDetail>>

}