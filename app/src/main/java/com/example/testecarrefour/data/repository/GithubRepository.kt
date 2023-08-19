package com.example.testecarrefour.data.repository

import com.example.testecarrefour.data.api.DataSource
import com.example.testecarrefour.domain.models.GitRepo
import com.example.testecarrefour.domain.models.User
import com.example.testecarrefour.domain.models.UserProfile
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GithubRepository @Inject constructor(private val dataSource: DataSource) {

    suspend fun getUsers(): List<User> {
        return dataSource.getUsers()
    }

    suspend fun getUserProfile(userName: String): UserProfile {
        return dataSource.getUserInfo(userName)
    }

    suspend fun getUserRepos(userName: String): List<GitRepo> {
        return dataSource.getUserRepos(userName)
    }
}