package com.example.codingchallenge.domain.usecase

import com.example.codingchallenge.domain.repository.UserRepository
import com.example.codingchallenge.data.model.User
import com.example.codingchallenge.data.model.UserDetail
import com.example.codingchallenge.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<Result<List<User>>> =
        userRepository.getUsers()
}

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Result<UserDetail>> =
        userRepository.getUserDetails(userId)
}