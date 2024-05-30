package com.example.mandirinews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mandirinews.service.model.Article
import com.example.mandirinews.service.model.NewsResponse
import com.example.mandirinews.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> get() = _articles

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            val call = getTopHeadlinesUseCase.execute("38b846222c584570b4ae592e379b68dd", "us")
            call.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.articles?.let {
                            _articles.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    // Handle error
                }
            })
        }
    }
}
