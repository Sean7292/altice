package com.devflowteam.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionDto,
    val budget: Long,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Long,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long,
)
