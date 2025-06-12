package com.example.movieshowapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieshowapp.api.TMDBApiService
import com.example.movieshowapp.viewmodel.MovieViewModel

@Composable
fun NavGraph(api: TMDBApiService, viewModel: MovieViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") { MovieListScreen(navController, viewModel) }
        composable("detail/{movieId}", arguments = listOf(navArgument("movieId") { type = NavType.IntType })) { backStack ->
            val movieId = backStack.arguments?.getInt("movieId") ?: return@composable
            MovieDetailScreen(movieId, api)
        }
    }
}