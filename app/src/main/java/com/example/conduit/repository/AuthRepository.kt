package com.example.conduit.repository

import com.example.api.data.ConduitService
import com.example.api.model.request.LoginRequest
import com.example.api.model.request.RegisterRequest
import com.example.api.model.response.UserResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private var conduitService: ConduitService
) {

    suspend fun login(user: LoginRequest): UserResponse {
        return conduitService.login(user)
    }

    suspend fun signUp(registerRequest: RegisterRequest): UserResponse {

        return conduitService.registerUser(registerRequest)
    }


}