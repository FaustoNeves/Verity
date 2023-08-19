package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.GitRepo
import com.example.testecarrefour.domain.models.User
import com.example.testecarrefour.domain.models.UserProfile
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{user}")
    suspend fun getUserProfile(@Path("user") userName: String): UserProfile

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") userName: String): List<GitRepo>
}