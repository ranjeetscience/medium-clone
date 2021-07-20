package com.example.conduit

import com.example.api.data.ApiClient
import com.example.api.data.ConduitService
import com.example.api.model.entity.RegisterUser
import com.example.api.model.request.RegisterRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.random.Random

class ConduitClientTest {

    val apiClient = ApiClient

    @Test
    fun `test for success of get articles`() {
        runBlocking {
            val articles = apiClient.buildClient().create(ConduitService::class.java).getArticles()
            assertNotNull(articles)
        }
    }

    @Test
    fun `test for success of registering  user`() {
        val registerUser =
            RegisterUser(
                "test_email_${Random.nextInt(100, 10000)}@test.com",
                "pass_test_123",
                "test_ran_${Random.nextInt(99, 9999)}"
            )
        runBlocking {
            val registeredUser = apiClient.buildClient().create(ConduitService::class.java)
                .registerUser(RegisterRequest(registerUser))

            assertEquals(registerUser.email,registerUser.email)
        }
    }
}