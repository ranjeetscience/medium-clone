package com.example.conduit.repository

import com.example.api.data.AuthApiService
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val authApiService: AuthApiService) {

    suspend fun getUserDetail() = authApiService.getUserDetail()
}