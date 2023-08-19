package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.GitRepo
import com.example.testecarrefour.domain.models.User
import com.example.testecarrefour.domain.models.UserProfile
import io.reactivex.rxjava3.core.Observable

interface DataSource {
    suspend fun getUsers(): List<User>
    suspend fun getUserInfo(userName: String): UserProfile

    suspend fun getUserRepos(userName: String): List<GitRepo>
}