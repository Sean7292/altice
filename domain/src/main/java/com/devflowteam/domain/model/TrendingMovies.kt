package com.devflowteam.domain.model

data class TrendingMovies(
    val page: Long,
    val movies: List<Movie>
)
