package com.example.api.data

import com.example.api.model.response.FavouriteResponse
import com.example.api.model.response.ProfileResponse
import com.example.api.model.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {

    @GET("user")
    suspend fun getUserDetail(): UserResponse

    //Profile
    @GET("profiles/{userID}")
    suspend fun getUser(@Path("userID") userId: String): ProfileResponse

    @POST("articles/{slug}/favorite")
    suspend fun markFavorite(): FavouriteResponse
}