package com.example.retrofit_practice_mvvm.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_practice_mvvm.Utils.NetworkUtils
import com.example.retrofit_practice_mvvm.api.QuoteAPI
import com.example.retrofit_practice_mvvm.db.QuoteDatabase
import com.example.retrofit_practice_mvvm.models.QuoteList
import java.lang.Exception

class QuoteRepository(
    private val quoteAPI: QuoteAPI,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes : LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = quoteAPI.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }
        else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)
        }

        }
    }
