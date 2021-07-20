package com.example.conduit.di

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.asLiveData
import com.example.api.data.ApiClient
import com.example.api.data.AuthApiService
import com.example.api.data.ConduitService
import com.example.conduit.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.collect
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun getConduitService(): ConduitService = ApiClient
        .buildClient()
        .create(ConduitService::class.java)

    @Singleton
    @Provides
    fun getAuthApiService(client: OkHttpClient): AuthApiService = ApiClient
        .buildAuthClient()
        .client(client)
        .build()
        .create(AuthApiService::class.java)

    @Singleton
    @Provides
    fun getHttpClient(dataStoreManager: SharedPreferences): OkHttpClient {
        val header = dataStoreManager.getString("auth_toke", "")
        return OkHttpClient.Builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()

                val request = original.newBuilder()
                    .header("authorization", "Token $header")
                    .method(original.method, original.body)
                    .build()
                return chain.proceed(request)
            }

        }).build()
    }


    @Provides
    @Singleton
    fun sharedPref(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )

}