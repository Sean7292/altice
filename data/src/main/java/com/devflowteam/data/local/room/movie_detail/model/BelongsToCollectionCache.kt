package com.devflowteam.data.local.room.movie_detail.model

import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollectionCache(
    val id: Long,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)
