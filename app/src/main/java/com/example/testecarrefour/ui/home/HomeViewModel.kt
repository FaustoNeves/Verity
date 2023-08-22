package com.example.testecarrefour.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecarrefour.data.repository.GithubRepository
import com.example.testecarrefour.domain.models.User
import com.example.testecarrefour.domain.models.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private val _usersList = MutableLiveData<List<User>>()
    val usersList = _usersList
    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile = _userProfile
    private val _requestThrowable = MutableLiveData<Throwable>()
    val requestThrowableOn = _requestThrowable
    private val _throwableOnGetUser = MutableLiveData<Boolean>(false)
    val throwableOnGetUser = _throwableOnGetUser
    private val _initializeNavigation = MutableLiveData(false)
    val initializeNavigation = _initializeNavigation

    fun getUsers() {
        viewModelScope.launch {
            try {
                usersList.postValue(repository.getUsers())
            } catch (exception: Exception) {
                _requestThrowable.postValue(exception)
            }
        }
    }

    fun getUser(userName: String) {
        if (userName.isNullOrBlank())
            _throwableOnGetUser.postValue(true)
        else
            viewModelScope.launch {
                try {
                    _userProfile.postValue(repository.getUserProfile(userName))
                    initializeNavigation.postValue(true)
                } catch (exception: Exception) {
                    _requestThrowable.postValue(exception)
                }
            }
    }
}