package com.devflowteam.domain.model

data class Movie(
    val adult: Boolean,
    val id: Long,
    val originalTitle: String,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
)
