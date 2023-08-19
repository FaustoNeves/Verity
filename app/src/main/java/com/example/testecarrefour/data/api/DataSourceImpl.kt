package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.GitRepo
import com.example.testecarrefour.domain.models.User
import com.example.testecarrefour.domain.models.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class DataSourceImpl @Inject constructor(private val githubApiService: GithubApiService) :
    DataSource {
    override suspend fun getUsers(): List<User> {
        return githubApiService.getUsers()
    }

    override suspend fun getUserInfo(userName: String): UserProfile {
        return githubApiService.getUserProfile(userName)
    }

    override suspend fun getUserRepos(userName: String): List<GitRepo> {
        return githubApiService.getUserRepos(userName)
    }
}