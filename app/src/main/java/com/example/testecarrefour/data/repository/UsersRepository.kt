package com.example.testecarrefour.data.repository

import com.example.testecarrefour.data.api.DataSource
import com.example.testecarrefour.domain.models.UsersResponse
import javax.inject.Inject

class UsersRepository @Inject constructor(private val dataSource: DataSource) {

    suspend fun getUsers(): List<UsersResponse> {
        return dataSource.getUsers()
    }
}