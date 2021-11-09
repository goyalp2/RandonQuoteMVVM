package com.example.randonquotemvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.randonquotemvvm.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuote(quotes : List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuote() : List<Result>
}