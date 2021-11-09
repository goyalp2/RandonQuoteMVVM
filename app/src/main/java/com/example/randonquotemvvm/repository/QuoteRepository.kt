package com.example.randonquotemvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randonquotemvvm.api.QuoteService
import com.example.randonquotemvvm.db.QuoteDatabase
import com.example.randonquotemvvm.models.QuoteList
import com.example.randonquotemvvm.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext : Context) {
    private val  quotesLiveData = MutableLiveData<QuoteList>()
    val quotes : LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(page : Int){
        if(NetworkUtils.isNetworkAvailable(applicationContext)){
            val result = quoteService.getQuotes(page)
            if(result?.body() != null){
                quoteDatabase.quoteDao().addQuote(result.body()!!.results)
                quotesLiveData.postValue(result.body())
        }
        else{
            val quotes = quoteDatabase.quoteDao().getQuote()
            val quoteList = QuoteList(1,1,1, quotes,1,1 )
            quotesLiveData.postValue(quoteList)
        }
        }
    }
}