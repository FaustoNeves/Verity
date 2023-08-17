package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.UsersResponse
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val githubApiService: GithubApiService) :
    DataSource {
    override suspend fun getUsers(): List<UsersResponse> {
        return githubApiService.getUsers()
    }
}