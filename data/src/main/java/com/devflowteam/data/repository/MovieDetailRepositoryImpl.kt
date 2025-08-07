package com.devflowteam.data.repository

import android.database.sqlite.SQLiteDiskIOException
import com.devflowteam.data.local.room.movie_detail.MovieDetailDao
import com.devflowteam.data.util.toDomain
import com.devflowteam.data.util.toEntity
import com.devflowteam.domain.model.MovieDetail
import com.devflowteam.domain.repository.MovieDetailRepository
import com.devflowteam.domain.util.error.DataError
import com.devflowteam.domain.util.error.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException

class MovieDetailRepositoryImpl(
    private val dao: MovieDetailDao
): MovieDetailRepository {

    override suspend fun upsert(movieDetail: MovieDetail): Result<Unit, DataError.Local> {
        return withContext(Dispatchers.IO) {
            try {
                dao.upsert(movieDetail.toEntity())
                Result.Success(Unit)
            } catch (e: CancellationException) {
                throw e
            } catch (e: SQLiteDiskIOException) {
                Result.Error(DataError.Local.FULL_DISK)
            } catch (e: Exception) {
                Result.Error(DataError.Local.UNKNOWN)
            }
        }
    }

    override suspend fun delete(movieDetail: MovieDetail): Result<Unit, DataError.Local> {
        return withContext(Dispatchers.IO) {
            try {
                dao.delete(movieDetail.toEntity())
                Result.Success(Unit)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Result.Error(DataError.Local.UNKNOWN)
            }
        }
    }

    override fun getAllMovieDetails(): Flow<List<MovieDetail>> {
        return dao.getAllMovieDetails().map { entryList ->
            entryList.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun getAllMovieDetailIds(): Flow<List<Long>> {
        return dao.getAllMovieDetailIds()
    }
}