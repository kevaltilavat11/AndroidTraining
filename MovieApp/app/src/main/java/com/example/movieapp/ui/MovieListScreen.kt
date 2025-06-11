package com.example.movieapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.viewmodel.MovieViewModel

@Composable
fun MovieListScreen(navController: NavHostController, viewModel: MovieViewModel) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    LazyColumn {
        items(movies) { movie ->
            movie?.let {
                ListItem(
                    headlineContent = { Text(it.title) },
                    supportingContent = { Text(it.overview.take(100) + "...") },
                    leadingContent = {
                        it.posterPath?.let { path ->
                            Image(
                                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500$path"),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail/${it.id}") }
                        .padding(8.dp)
                )
            }
        }
    }
}
