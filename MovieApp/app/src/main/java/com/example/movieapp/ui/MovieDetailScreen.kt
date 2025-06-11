package com.example.movieapp.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.api.TMDBApiService
import com.example.movieapp.model.MovieDetail


@Composable
fun MovieDetailScreen(movieId: Int, api: TMDBApiService) {
    var movie by remember { mutableStateOf<MovieDetail?>(null) }
    LaunchedEffect(movieId) {
        movie = api.getMovieDetails(movieId, "https://api.themoviedb.org/3/")
    }
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
            Text("Release Date: ${it.releaseDate}", style = MaterialTheme.typography.bodyMedium)
            Text("Genres: ${it.genres.joinToString { g -> g.name }}", style = MaterialTheme.typography.bodyMedium)
            Text("Runtime: ${it.runtime} minutes", style = MaterialTheme.typography.bodyMedium)
            Text(it.overview, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 8.dp))
        }
    }
}