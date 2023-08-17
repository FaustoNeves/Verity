package com.example.testecarrefour.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testecarrefour.data.repository.UsersRepository
import com.example.testecarrefour.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {

    val user = MutableLiveData<List<User>>()
    val requestThrowable = MutableLiveData<Throwable>()
    private val compositeDisposable = CompositeDisposable()
    fun getUsers() {
        val subscription = repository
            .getUsers()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    user.postValue(it)
                },
                {
                    requestThrowable.postValue(it)
                }
            )
        compositeDisposable.add(subscription)
    }

    fun disposeSubscriptions() {
        compositeDisposable.clear()
    }
}