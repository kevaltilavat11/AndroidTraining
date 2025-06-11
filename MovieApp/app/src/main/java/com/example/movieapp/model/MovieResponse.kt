package com.example.movieapp.model

data class MovieResponse(val results: List<Movie>, val page: Int, val total_pages: Int)
