package com.example.movieshowapp.api

import com.example.movieshowapp.model.MovieDetail
import com.example.movieshowapp.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int, @Query("https://api.themoviedb.org/3/") key: String): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int, @Query("https://api.themoviedb.org/3/") key: String): MovieDetail
}