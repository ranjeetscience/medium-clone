package com.example.conduit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.entity.User
import com.example.api.model.request.RegisterRequest
import com.example.conduit.repository.AuthRepository
import com.example.conduit.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    var _user: MutableLiveData<User> = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user


    fun signUp(userRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                _user.value = authRepository.signUp(userRequest).user
            } catch (e: Exception) {
                Log.e("error",e.toString())
            }
        }
    }

}