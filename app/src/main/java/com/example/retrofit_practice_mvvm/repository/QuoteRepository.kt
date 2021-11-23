package com.example.retrofit_practice_mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_practice_mvvm.Utils.NetworkUtils
import com.example.retrofit_practice_mvvm.api.QuoteAPI
import com.example.retrofit_practice_mvvm.db.QuoteDatabase
import com.example.retrofit_practice_mvvm.models.QuoteList
import kotlin.Exception

class QuoteRepository(
    private val quoteAPI: QuoteAPI,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes : LiveData<Response<QuoteList>>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val result = quoteAPI.getQuotes(page)
                if (result?.body() != null) {
                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                    quotesLiveData.postValue(Response.Success(result.body()))
            }

            }
            catch (e: Exception) {
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }
        }
        else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes, 1, 1)
            quotesLiveData.postValue(Response.Success(quoteList))
        }

        }
    suspend fun getQuotesBackground() {
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteAPI.getQuotes(randomNumber)
        if (result?.body() != null) {
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
        }
    }
    }
