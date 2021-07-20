package com.example.conduit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.entity.Article
import com.example.conduit.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val feedRepository: FeedRepository) : ViewModel() {

    var _feeds: MutableLiveData<List<Article>> = MutableLiveData()
    val feed: LiveData<List<Article>>
        get() = _feeds

    init {
        loadFeed()
    }

    private fun loadFeed() {
        viewModelScope.launch {
            try {
                _feeds.value = feedRepository.getFeed().articles
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

}