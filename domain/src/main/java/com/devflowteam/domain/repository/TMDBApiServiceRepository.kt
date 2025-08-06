package com.devflowteam.domain.repository

import com.devflowteam.domain.model.MovieDetail
import com.devflowteam.domain.model.TrendingMovies
import com.devflowteam.domain.util.Time
import com.devflowteam.domain.util.error.DataError
import com.devflowteam.domain.util.error.Result

interface TMDBApiServiceRepository {

    suspend fun getTrendingMovies(
        time: Time
    ): Result<TrendingMovies, DataError.Network>

    suspend fun getMovieDetail(
        id: Long
    ): Result<MovieDetail, DataError.Network>
}