package com.example.retrofit_practice_mvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val quoteID:Int,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
)