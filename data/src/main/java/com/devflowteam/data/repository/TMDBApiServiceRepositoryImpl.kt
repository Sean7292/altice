package com.devflowteam.data.repository

import com.devflowteam.data.remote.TMDBApiService
import com.devflowteam.data.util.toDomain
import com.devflowteam.domain.model.MovieDetail
import com.devflowteam.domain.model.TrendingMovies
import com.devflowteam.domain.repository.TMDBApiServiceRepository
import com.devflowteam.domain.util.Time
import com.devflowteam.domain.util.error.DataError
import com.devflowteam.domain.util.error.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class TMDBApiServiceRepositoryImpl(
    private val tmdbApiService: TMDBApiService
): TMDBApiServiceRepository {

    override suspend fun getTrendingMovies(time: String): Result<TrendingMovies, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val response = tmdbApiService.getTrendingMovies(time)

                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!.toDomain())
                } else {
                    Result.Error(DataError.Network.SERVER)
                }
            } catch (e: IOException) {
                Result.Error(DataError.Network.NO_INTERNET)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }

    override suspend fun getMovieDetail(id: Long): Result<MovieDetail, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val response = tmdbApiService.getMovieDetail(id)

                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!.toDomain())
                } else {
                    Result.Error(DataError.Network.SERVER)
                }
            } catch (e: IOException) {
                Result.Error(DataError.Network.NO_INTERNET)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }
}