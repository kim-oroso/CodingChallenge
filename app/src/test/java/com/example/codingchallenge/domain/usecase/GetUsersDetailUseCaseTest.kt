package com.example.codingchallenge.domain.usecase

import com.example.codingchallenge.data.model.UserDetail
import com.example.codingchallenge.domain.repository.UserRepository
import com.example.codingchallenge.utils.Result
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import java.util.Date

class GetUserDetailsUseCaseTest {

    private lateinit var getUserDetailsUseCase: GetUserDetailsUseCase
    private val userRepository: UserRepository = mock()

    @Before
    fun setUp() {
        getUserDetailsUseCase = GetUserDetailsUseCase(userRepository)
    }

    @Test
    fun `invoke should return user details`() = runTest {
        val mockUsers = UserDetail(1, "John Doe", "johndoe", "https://avatars.githubusercontent.com/u/1?v=4", "","","","", "", "","", "", "", "","","user", false, "jonh doe", "company", "blog", "san francisco", "email", "hireble", "bio", "twitter", 2,1,2,12,
            Date(), Date()
        )
        `when`(userRepository.getUserDetails("1")).thenReturn(flow {
            emit(Result.Success(mockUsers))
        })

        val result = getUserDetailsUseCase("1")

        result.collect { resource ->
            assert(resource is Result.Success)
            assert((resource as Result.Success).data == mockUsers)
        }
    }

    @Test
    fun `invoke should return error`() = runTest {
        `when`(userRepository.getUserDetails("1")).thenReturn(flow {
            emit(Result.Error("User not found"))
        })

        val result = getUserDetailsUseCase("1")

        result.collect { resource ->
            assert(resource is Result.Error)
            assert((resource as Result.Error).message == "User not found")
        }
    }
}