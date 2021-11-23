package com.example.retrofit_practice_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_practice_mvvm.adapters.QuoteAdapter
import com.example.retrofit_practice_mvvm.api.QuoteAPI
import com.example.retrofit_practice_mvvm.api.RetrofitHelper
import com.example.retrofit_practice_mvvm.repository.QuoteRepository
import com.example.retrofit_practice_mvvm.repository.Response
import com.example.retrofit_practice_mvvm.viewmodel.MainViewModel
import com.example.retrofit_practice_mvvm.viewmodel.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.codesRecyclerView)
        val quoteAdapter = QuoteAdapter()
        recyclerView.adapter = quoteAdapter
        val repository =(application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            when (it) {
                is Response.Loading -> {

                }
                is Response.Success -> {
                    quoteAdapter.submitList(it.data?.results)
                }
                is Response.Error -> {
                    it.errorMessage
                    Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}