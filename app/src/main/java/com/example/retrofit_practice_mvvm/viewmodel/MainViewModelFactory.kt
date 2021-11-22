package com.example.retrofit_practice_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_practice_mvvm.repository.QuoteRepository

class MainViewModelFactory(private val repository: QuoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
    }
}