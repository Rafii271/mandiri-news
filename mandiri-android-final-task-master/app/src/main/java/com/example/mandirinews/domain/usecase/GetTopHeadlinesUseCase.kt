package com.example.mandirinews.domain.usecase

import com.example.mandirinews.service.model.NewsResponse
import com.example.mandirinews.service.repository.NewsRepository
import retrofit2.Call

class GetTopHeadlinesUseCase(private val repository: NewsRepository) {
    fun execute(apiKey: String, country: String): Call<NewsResponse> {
        return repository.getTopHeadlines(apiKey, country)
    }
}
