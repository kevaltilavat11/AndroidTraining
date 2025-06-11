package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.movieapp.api.TMDBApiService
import com.example.movieapp.ui.AppNavGraph
import com.example.movieapp.viewmodel.MovieViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(TMDBApiService::class.java)
        val viewModel = MovieViewModel(api)

        setContent {
            MaterialTheme {
                AppNavGraph(api, viewModel)
            }
        }
    }
}