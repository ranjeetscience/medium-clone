package com.example.api.data

import com.example.api.model.entity.User
import com.example.api.model.request.LoginRequest
import com.example.api.model.request.RegisterRequest
import com.example.api.model.response.ArticleResponse
import com.example.api.model.response.FavouriteResponse
import com.example.api.model.response.ProfileResponse
import com.example.api.model.response.UserResponse
import retrofit2.http.*

interface ConduitService {

    //Register
    @POST("users")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): UserResponse

    //Login
    @POST("users/login")
    suspend fun login(@Body user: LoginRequest): UserResponse

    @GET("articles")
    suspend fun getArticles(@Query("tag") tag: String? = null): ArticleResponse



}