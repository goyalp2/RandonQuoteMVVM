package com.example.randonquotemvvm

import android.app.Application
import com.example.randonquotemvvm.api.QuoteService
import com.example.randonquotemvvm.api.RetrofitHelper
import com.example.randonquotemvvm.db.QuoteDatabase
import com.example.randonquotemvvm.repository.QuoteRepository
import kotlinx.coroutines.*

class QuoteApplication : Application() {

    lateinit var  quoteRepository: QuoteRepository
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    @InternalCoroutinesApi
    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}