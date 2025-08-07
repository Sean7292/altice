package com.devflowteam.domain.repository

import com.devflowteam.domain.model.MovieDetail
import com.devflowteam.domain.util.error.DataError
import com.devflowteam.domain.util.error.Result
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun upsert(
        movieDetail: MovieDetail
    ): Result<Unit, DataError.Local>

    suspend fun delete(
        movieDetail: MovieDetail
    ): Result<Unit, DataError.Local>

    fun getAllMovieDetails(): Flow<List<MovieDetail>>

    fun getAllMovieDetailIds(): Flow<List<Long>>
}