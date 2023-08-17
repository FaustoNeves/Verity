package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.UsersResponse
import retrofit2.http.GET

interface GithubApiService {

    @GET("users")
    suspend fun getUsers(): List<UsersResponse>
}