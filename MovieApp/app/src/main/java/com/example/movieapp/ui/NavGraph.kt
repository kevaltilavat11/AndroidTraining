package com.example.movieapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.api.TMDBApiService
import com.example.movieapp.viewmodel.MovieViewModel

@Composable
fun AppNavGraph(api: TMDBApiService, viewModel: MovieViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") {
            MovieListScreen(navController, viewModel)
        }
        composable("detail/{movieId}", arguments = listOf(navArgument("movieId") { type = NavType.IntType })) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            MovieDetailScreen(movieId, api)
        }
    }
}