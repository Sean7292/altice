package com.devflowteam.data.local.room.movie_detail.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyCache(
    val id: Long,
    val logoPath: String,
    val name: String,
    val originalCountry: String
)
