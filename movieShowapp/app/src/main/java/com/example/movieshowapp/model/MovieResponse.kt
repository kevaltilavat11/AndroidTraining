package com.example.movieshowapp.model

data class MovieResponse(val results: List<Movie>, val page: Int, val total_pages: Int)
