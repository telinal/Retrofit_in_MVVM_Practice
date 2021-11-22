package com.example.retrofit_practice_mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_practice_mvvm.api.QuoteAPI
import com.example.retrofit_practice_mvvm.models.QuoteList
import java.lang.reflect.Array.get

class QuoteRepository(private val quoteAPI: QuoteAPI) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes : LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        val result = quoteAPI.getQuotes(page)
        if (result?.body() != null) {
                quotesLiveData.postValue(result.body())
        }
    }
}