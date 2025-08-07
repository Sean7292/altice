package com.devflowteam.data.local.room.movie_detail

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {

    @Upsert
    suspend fun upsert(movieDetailEntity: MovieDetailEntity)

    @Delete
    suspend fun delete(movieDetailEntity: MovieDetailEntity)

    @Query("SELECT * FROM MovieDetailEntity")
    fun getAllMovieDetails(): Flow<List<MovieDetailEntity>>

    @Query("SELECT id FROM MovieDetailEntity")
    fun getAllMovieDetailIds(): Flow<List<Long>>
}