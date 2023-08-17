package com.example.testecarrefour.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testecarrefour.data.repository.UsersRepository
import com.example.testecarrefour.domain.models.UsersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {

    suspend fun getUsers(): List<UsersResponse> {
        try {
            return repository.getUsers()
        } catch (exception: Exception) {
            Log.e("Users exception", exception.message!!)
            throw exception
        }
    }
}