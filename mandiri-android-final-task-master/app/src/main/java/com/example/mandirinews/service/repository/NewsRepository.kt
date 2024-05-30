package com.example.mandirinews.service.repository

import com.example.mandirinews.service.api.Client
import com.example.mandirinews.service.model.NewsResponse
import retrofit2.Call

class NewsRepository {
    private val apiService = Client.retrofitInstance

    fun getTopHeadlines(apiKey: String, country: String): Call<NewsResponse> {
        return apiService.getTopHeadlines(apiKey, country)
    }
}
