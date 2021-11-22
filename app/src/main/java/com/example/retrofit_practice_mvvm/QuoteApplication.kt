package com.example.retrofit_practice_mvvm

import android.app.Application
import com.example.retrofit_practice_mvvm.api.QuoteAPI
import com.example.retrofit_practice_mvvm.api.RetrofitHelper
import com.example.retrofit_practice_mvvm.db.QuoteDatabase
import com.example.retrofit_practice_mvvm.repository.QuoteRepository

class QuoteApplication: Application() {
    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteAPI = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteAPI, database, applicationContext)
    }
}