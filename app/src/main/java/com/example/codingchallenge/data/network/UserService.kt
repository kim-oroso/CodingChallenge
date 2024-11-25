package com.example.codingchallenge.data.network

import com.example.codingchallenge.data.model.User
import com.example.codingchallenge.data.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{USER_ID}")
    suspend fun getUserDetails(@Path("USER_ID") login: String): UserDetail

}