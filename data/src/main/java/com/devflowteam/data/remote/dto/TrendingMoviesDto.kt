package com.devflowteam.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingMoviesDto(
    val page: Long,
    @SerialName("results")
    val movies: List<MovieDto>,
    @SerialName("total_pages")
    val totalPages: Long,
    @SerialName("total_results")
    val totalResults: Long,
)