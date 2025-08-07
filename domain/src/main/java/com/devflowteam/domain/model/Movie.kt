package com.devflowteam.domain.model

data class Movie(
    val isFavorite: Boolean = false,
    val adult: Boolean,
    val id: Long,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
)
