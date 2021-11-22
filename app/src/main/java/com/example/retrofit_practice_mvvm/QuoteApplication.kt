package com.example.retrofit_practice_mvvm

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.retrofit_practice_mvvm.api.QuoteAPI
import com.example.retrofit_practice_mvvm.api.RetrofitHelper
import com.example.retrofit_practice_mvvm.db.QuoteDatabase
import com.example.retrofit_practice_mvvm.repository.QuoteRepository
import com.example.retrofit_practice_mvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication: Application() {
    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quoteAPI = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteAPI, database, applicationContext)
    }
}