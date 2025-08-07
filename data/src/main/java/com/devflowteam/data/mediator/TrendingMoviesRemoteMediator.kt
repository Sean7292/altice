package com.devflowteam.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.devflowteam.data.local.room.movie.MovieDao
import com.devflowteam.data.local.room.movie.MovieDatabase
import com.devflowteam.data.local.room.movie.MovieEntity
import com.devflowteam.data.remote.TMDBApiService
import com.devflowteam.data.remote.dto.TrendingMoviesDto
import com.devflowteam.data.util.toEntity
import com.devflowteam.domain.util.Time
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TrendingMoviesRemoteMediator(
    private val api: TMDBApiService,
    private val database: MovieDatabase,
    private val time: Time
): RemoteMediator<Int, MovieEntity>() {

    private val dao: MovieDao = database.movieDao()
    private var totalPages: Int? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> { 1 }
            LoadType.PREPEND -> {
                return MediatorResult.Success(
                    endOfPaginationReached = true
                )
            }
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) 1 else (state.pages.size + 1)
            }
        }

        return try {
            val response = api.getTrendingMovies(time.type)

            if (!response.isSuccessful || response.body() == null) {
                return MediatorResult.Error(retrofit2.HttpException(response))
            }

            val moviesDto: TrendingMoviesDto = response.body()!!

            totalPages = moviesDto.totalPages.toInt()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.clearAll()
                }

                val entities: List<MovieEntity> = moviesDto.movies.map {
                    it.toEntity()
                }
                dao.upsertAll(entities)
            }

            val reachedEnd = page >= (totalPages ?: Int.MAX_VALUE)
            MediatorResult.Success(endOfPaginationReached = reachedEnd)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}