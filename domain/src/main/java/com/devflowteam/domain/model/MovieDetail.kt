package com.devflowteam.domain.model

data class MovieDetail(
    val isFavorite: Boolean = false,
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollection,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val originCountry: List<String>,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)
