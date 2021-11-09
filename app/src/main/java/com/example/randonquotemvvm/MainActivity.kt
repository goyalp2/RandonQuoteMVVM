package com.example.randonquotemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.randonquotemvvm.api.QuoteService
import com.example.randonquotemvvm.api.RetrofitHelper
import com.example.randonquotemvvm.repository.QuoteRepository
import com.example.randonquotemvvm.viewmodels.MainViewModel
import com.example.randonquotemvvm.viewmodels.MainViewModelFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Toast.makeText(this, it.results.size.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}