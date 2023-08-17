package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface GithubApiService {

    @GET("users")
    fun getUsers(): Observable<List<User>>
}