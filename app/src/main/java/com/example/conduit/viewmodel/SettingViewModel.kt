package com.example.conduit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.entity.User
import com.example.conduit.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {

    var _user: MutableLiveData<User> = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        viewModelScope.launch {
            _user.value = profileRepository.getUserDetail().user
        }
    }
}