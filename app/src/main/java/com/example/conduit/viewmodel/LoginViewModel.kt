package com.example.conduit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.entity.LoginUser
import com.example.api.model.request.LoginRequest
import com.example.api.model.response.UserResponse
import com.example.conduit.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) :
    ViewModel() {

    var _userResponse: MutableLiveData<UserResponse> = MutableLiveData()
    val userResponse: LiveData<UserResponse>
        get() = _userResponse

    fun login(email: String, password: String) {

        val user = LoginUser(email = email, password = password)

        viewModelScope.launch {
            try {
                _userResponse.value = authRepository.login(LoginRequest(user))
            } catch (e: Exception) {
                e.message?.let { Log.e("error", it) }
            }
        }
    }
}