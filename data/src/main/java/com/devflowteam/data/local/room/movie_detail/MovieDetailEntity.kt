package com.devflowteam.data.local.room.movie_detail

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devflowteam.data.local.room.movie_detail.model.BelongsToCollectionCache
import com.devflowteam.data.local.room.movie_detail.model.GenreCache
import com.devflowteam.data.local.room.movie_detail.model.ProductionCompanyCache

@Entity
data class MovieDetailEntity(
    @PrimaryKey
    val id: Long,

    val isFavorite: Boolean,
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollectionCache,
    val budget: Long,
    val genres: List<GenreCache>,
    val homepage: String,
    val originCountry: List<String>,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyCache>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)