package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapp.api.TMDBApiService
import com.example.movieapp.paging.MoviePagingSource

class MovieViewModel(private val api: TMDBApiService) : ViewModel() {
    val movies = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(api, "https://api.themoviedb.org/3/")
    }.flow.cachedIn(viewModelScope)
}