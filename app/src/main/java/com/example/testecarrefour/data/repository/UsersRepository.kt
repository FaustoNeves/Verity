package com.example.testecarrefour.data.repository

import com.example.testecarrefour.data.api.DataSource
import com.example.testecarrefour.domain.models.User
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UsersRepository @Inject constructor(private val dataSource: DataSource) {

    fun getUsers(): Observable<List<User>> {
        return dataSource.getUsers()
    }
}