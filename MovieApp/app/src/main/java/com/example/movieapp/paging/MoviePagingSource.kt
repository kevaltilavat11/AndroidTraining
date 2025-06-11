package com.example.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.api.TMDBApiService
import com.example.movieapp.model.Movie

class MoviePagingSource(private val api: TMDBApiService, private val apiKey: String) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = api.getPopularMovies(page, apiKey)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.total_pages) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = state.anchorPosition
}