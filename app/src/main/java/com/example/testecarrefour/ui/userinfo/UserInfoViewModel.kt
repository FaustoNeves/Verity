package com.example.testecarrefour.ui.userinfo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecarrefour.data.repository.GithubRepository
import com.example.testecarrefour.domain.models.GitRepo
import com.example.testecarrefour.domain.models.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val repository: GithubRepository) :
    ViewModel() {

    val requestThrowable = MutableLiveData<Throwable>()
    val userProfileLiveData = MutableLiveData<UserProfile>()
    val reposListLiveData = MutableLiveData<List<GitRepo>>()
    val isRequestCompleted = MutableLiveData<Boolean>()

    fun getUserProfile(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val getUserProfileJob =
                    async { userProfileLiveData.postValue(repository.getUserProfile(userName)) }
                val getReposListJob =
                    async { reposListLiveData.postValue(repository.getUserRepos(userName)) }

                listOf(async {
                    getUserProfileJob.await()
                }, async {
                    getReposListJob.await()
                }).awaitAll()

                isRequestCompleted.postValue(true)
            } catch (exception: Exception) {
                requestThrowable.postValue(exception)
            }
        }
    }
}