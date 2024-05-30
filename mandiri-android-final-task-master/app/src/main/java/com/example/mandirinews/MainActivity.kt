package com.example.mandirinews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.mandirinews.presentation.ui.NewsScreen
import com.example.mandirinews.presentation.viewmodel.NewsViewModel
import com.example.mandirinews.domain.usecase.GetTopHeadlinesUseCase
import com.example.mandirinews.service.repository.NewsRepository
import com.example.mandirinews.presentation.ui.theme.NewsDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = NewsRepository()
        val getTopHeadlinesUseCase = GetTopHeadlinesUseCase(repository)
        val newsViewModelFactory = NewsViewModelFactory(getTopHeadlinesUseCase)
        val newsViewModel: NewsViewModel by viewModels { newsViewModelFactory }

        setContent {
            NewsDemoTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NewsScreen(newsViewModel, "Mandiri News")
                }
            }
        }
    }
}

// Create a ViewModelFactory for NewsViewModel
class NewsViewModelFactory(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(getTopHeadlinesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
