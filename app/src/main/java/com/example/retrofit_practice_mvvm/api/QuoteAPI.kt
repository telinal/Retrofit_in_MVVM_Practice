package com.example.retrofit_practice_mvvm.api

import com.example.retrofit_practice_mvvm.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page : Int) : Response<QuoteList>

    // BASE URL + "/quotes?page=1"
}