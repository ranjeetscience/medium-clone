package com.example.api.model.request


import com.example.api.model.entity.RegisterUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    @Json(name = "user")
    val registerUser: RegisterUser
)