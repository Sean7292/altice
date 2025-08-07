package com.devflowteam.domain.model

data class TrendingMovies(
    val page: Long,
    val movies: List<Movie>,
    val totalPages: Long,
    val totalResults: Long
)
