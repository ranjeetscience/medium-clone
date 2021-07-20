package com.example.conduit.repository

import com.example.api.data.ConduitService
import javax.inject.Inject

class FeedRepository @Inject constructor(private val conduitService: ConduitService) {

    suspend fun getFeed() = conduitService.getArticles()
}