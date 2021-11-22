package com.example.retrofit_practice_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_practice_mvvm.api.QuoteAPI
import com.example.retrofit_practice_mvvm.api.RetrofitHelper
import com.example.retrofit_practice_mvvm.repository.QuoteRepository
import com.example.retrofit_practice_mvvm.viewmodel.MainViewModel
import com.example.retrofit_practice_mvvm.viewmodel.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteAPI = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val repository = QuoteRepository(quoteAPI)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Log.d("TELINACODE", it.results.toString())
        })



    }
}