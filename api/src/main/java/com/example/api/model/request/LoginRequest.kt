package com.example.api.model.request


import com.example.api.model.entity.LoginUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "user")
    val loginUser: LoginUser
)