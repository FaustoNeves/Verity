package com.example.testecarrefour.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecarrefour.data.repository.GithubRepository
import com.example.testecarrefour.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    val user = MutableLiveData<List<User>>()
    val requestThrowable = MutableLiveData<Throwable>()
    fun getUsers() {
        viewModelScope.launch {
            try {
                user.postValue(repository.getUsers())
            } catch (exception: Exception) {
                requestThrowable.postValue(exception)
            }
        }
    }
}