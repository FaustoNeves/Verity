package com.example.testecarrefour.ui.reposinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testecarrefour.data.repository.GithubRepository
import com.example.testecarrefour.domain.models.GitRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(private val repository: GithubRepository) :
    ViewModel() {

    private val _reposListLiveData = MutableLiveData<List<GitRepo>>()
    val reposListLiveData = _reposListLiveData

    private val _requestThrowable = MutableLiveData<Throwable>()
    val requestThrowable = _requestThrowable

    private val _isRequestCompleted = MutableLiveData<Boolean>()
    val isRequestCompleted = _isRequestCompleted

    fun getRepositoriesList(userName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                async { _reposListLiveData.postValue(repository.getUserRepos(userName)) }.await()
                _isRequestCompleted.postValue(true)
            } catch (exception: Exception) {
                _requestThrowable.postValue(exception)
            }
        }
    }
}