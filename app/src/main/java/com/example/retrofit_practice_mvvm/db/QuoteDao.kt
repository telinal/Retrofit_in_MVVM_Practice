package com.example.retrofit_practice_mvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofit_practice_mvvm.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Result>
}