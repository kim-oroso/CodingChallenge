package com.example.codingchallenge.domain.usecase

import com.example.codingchallenge.data.model.User
import com.example.codingchallenge.domain.repository.UserRepository
import com.example.codingchallenge.utils.Result
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class GetUsersUseCaseTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private val userRepository: UserRepository = mock()

    @Before
    fun setUp() {
        getUsersUseCase = GetUsersUseCase(userRepository)
    }

    @Test
    fun `invoke should return list of users`() = runTest {
        val users = listOf( User(1, "John Doe", "johndoe", "https://avatars.githubusercontent.com/u/1?v=4", "","","","", "", "","", "", "", "","","user", false)  )
        `when`(userRepository.getUsers()).thenReturn(flow {
            emit(Result.Success(users))
        })

        val result = getUsersUseCase()

        result.collect { resource ->
            assert(resource is Result.Success)
            assert((resource as Result.Success).data == users)
        }
    }

    @Test
    fun `invoke should return error`() = runTest {
        `when`(userRepository.getUsers()).thenReturn(flow {
            emit(Result.Error("Network error"))
        })

        val result = getUsersUseCase()

        result.collect { resource ->
            assert(resource is Result.Error)
            assert((resource as Result.Error).message == "Network error")
        }
    }
}
