package com.example.movieapp.api

import com.example.movieapp.model.MovieDetail
import com.example.movieapp.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("https://api.themoviedb.org/3/") apiKey: String
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("https://api.themoviedb.org/3/") apiKey: String
    ): MovieDetail
}