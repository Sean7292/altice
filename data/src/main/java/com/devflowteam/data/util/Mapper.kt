package com.devflowteam.data.util

import com.devflowteam.data.remote.dto.BelongsToCollectionDto
import com.devflowteam.data.remote.dto.GenreDto
import com.devflowteam.data.remote.dto.MovieDetailDto
import com.devflowteam.data.remote.dto.MovieDto
import com.devflowteam.data.remote.dto.ProductionCompanyDto
import com.devflowteam.data.remote.dto.TrendingMoviesDto
import com.devflowteam.domain.model.BelongsToCollection
import com.devflowteam.domain.model.Genre
import com.devflowteam.domain.model.Movie
import com.devflowteam.domain.model.MovieDetail
import com.devflowteam.domain.model.ProductionCompany
import com.devflowteam.domain.model.TrendingMovies

fun TrendingMoviesDto.toDomain() = TrendingMovies(
    page = page,
    movies = movies.map { it.toDomain() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun MovieDetailDto.toDomain() = MovieDetail(
    adult = adult,
    backdropPath = backdropPath,
    belongsToCollection = belongsToCollection.toDomain(),
    budget = budget,
    genres = genres.map { it.toDomain() },
    homepage = homepage,
    id = id,
    originCountry = originCountry,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toDomain() },
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun BelongsToCollectionDto.toDomain() = BelongsToCollection(
    id = id,
    name = name,
    posterPath = posterPath,
    backdropPath = backdropPath
)

fun GenreDto.toDomain() = Genre(
    id = id,
    name = name
)

fun MovieDto.toDomain() = Movie(
    adult = adult,
    id = id,
    originalTitle = originalTitle,
    posterPath = posterPath,
    title = title,
    voteAverage = voteAverage
)

fun ProductionCompanyDto.toDomain() = ProductionCompany(
    id = id,
    logoPath = logoPath,
    name = name,
    originalCountry = originCountry
)
