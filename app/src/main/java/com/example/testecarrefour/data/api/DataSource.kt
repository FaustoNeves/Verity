package com.example.testecarrefour.data.api

import com.example.testecarrefour.domain.models.User
import io.reactivex.rxjava3.core.Observable

interface DataSource {
    fun getUsers(): Observable<List<User>>
}