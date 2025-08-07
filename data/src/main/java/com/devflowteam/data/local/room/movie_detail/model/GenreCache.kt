package com.devflowteam.data.local.room.movie_detail.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreCache(
    val id: Long,
    val name: String
)
