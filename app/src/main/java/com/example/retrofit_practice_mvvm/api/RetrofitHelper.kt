package com.example.retrofit_practice_mvvm.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.retrofit_practice_mvvm.BuildConfig
import okhttp3.*
import okio.Buffer

import java.io.IOException
import java.util.concurrent.TimeUnit


object RetrofitHelper {
    const val BASE_URL = "https://quotable.io/"
    // the OKHTTP is logging interceptor retrofit: it is use display the response of network calls
    const val OKHTTP = "okhttp"
    var okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request: Request = chain.request()
            if (BuildConfig.DEBUG) {
                Log.d(OKHTTP, request.method().toString() + " " + request.url())
                Log.d(OKHTTP, "" + request.header("Cookie"))
                val buffer = Buffer()
                request.body()?.writeTo(buffer)
                Log.d(OKHTTP, "Payload- " + buffer.readUtf8())
            }
            chain.proceed(request)
        }
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}