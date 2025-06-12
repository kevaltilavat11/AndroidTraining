package com.example.movieshowapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movieshowapp.api.TMDBApiService
import com.example.movieshowapp.model.MovieDetail

@Composable
fun MovieDetailScreen(movieId: Int, api: TMDBApiService) {
    var movie by remember { mutableStateOf<MovieDetail?>(null) }
    LaunchedEffect(movieId) { movie = api.getMovieDetails(movieId, "https://api.themoviedb.org/3/") }
    movie?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(it.title, style = MaterialTheme.typography.headlineLarge)
            it.posterPath?.let { path ->
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500$path"),
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
            Text("Release Date: ${it.releaseDate}")
            Text("Genres: ${it.genres.joinToString { g -> g.name }}")
            Text("Runtime: ${it.runtime} min")
            Text(it.overview, modifier = Modifier.padding(top = 8.dp))
        }
    }
}