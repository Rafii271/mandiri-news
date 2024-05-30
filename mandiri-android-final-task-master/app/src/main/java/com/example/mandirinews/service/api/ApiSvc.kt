package com.example.mandirinews.service.api

import com.example.mandirinews.service.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSvc {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Call<NewsResponse>
}