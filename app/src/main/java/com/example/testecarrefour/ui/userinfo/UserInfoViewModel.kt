package com.example.testecarrefour.ui.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecarrefour.data.repository.GithubRepository
import com.example.testecarrefour.domain.models.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val repository: GithubRepository) :
    ViewModel() {

    private val _requestThrowable = MutableLiveData<Throwable>()
    val requestThrowable = _requestThrowable

    private val _userProfileLiveData = MutableLiveData<UserProfile>()
    val userProfileLiveData = _userProfileLiveData

    private val _isRequestSucceeded = MutableLiveData<Boolean>()
    val isRequestSucceded = _isRequestSucceeded

    fun getUserProfile(userName: String) {
        viewModelScope.launch {
            try {
                async { _userProfileLiveData.postValue(repository.getUserProfile(userName)) }.await()
                _isRequestSucceeded.postValue(true)
            } catch (exception: Exception) {
                _requestThrowable.postValue(exception)
            }
        }
    }
}