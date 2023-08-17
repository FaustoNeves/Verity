package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.User
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val githubApiService: GithubApiService) :
    DataSource {
    override fun getUsers(): Observable<List<User>> {
        return githubApiService.getUsers()
    }
}