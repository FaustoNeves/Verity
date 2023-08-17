package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.UsersResponse

interface DataSource {
    suspend fun getUsers(): List<UsersResponse>
}