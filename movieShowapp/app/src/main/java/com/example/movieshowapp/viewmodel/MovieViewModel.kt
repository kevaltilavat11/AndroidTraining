package com.example.movieshowapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieshowapp.api.TMDBApiService
import com.example.movieshowapp.paging.MoviePagingSource

class MovieViewModel(api: TMDBApiService) : ViewModel() {
    val movies = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(api, "https://api.themoviedb.org/3/")
    }.flow.cachedIn(viewModelScope)
}