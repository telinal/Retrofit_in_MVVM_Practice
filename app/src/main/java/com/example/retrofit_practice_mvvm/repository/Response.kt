package com.example.retrofit_practice_mvvm.repository

import com.example.retrofit_practice_mvvm.models.QuoteList
//Generic implementation

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}
