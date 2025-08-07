package com.devflowteam.data.local.room.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Long,

    val isFavorite: Boolean = false,
    val adult: Boolean,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
)
