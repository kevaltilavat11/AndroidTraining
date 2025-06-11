package com.example.movieapp.model
import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val title: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    val genres: List<Genre>,
    val runtime: Int,
    @SerializedName("poster_path") val posterPath: String?
)

data class Genre(val name: String)