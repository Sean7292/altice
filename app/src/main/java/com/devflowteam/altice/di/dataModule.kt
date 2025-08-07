package com.devflowteam.altice.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.devflowteam.altice.BuildConfig
import com.devflowteam.data.local.room.movie.MovieDatabase
import com.devflowteam.data.local.room.movie.MovieEntity
import com.devflowteam.data.local.room.movie_detail.MovieDetailDatabase
import com.devflowteam.data.mediator.TrendingMoviesRemoteMediator
import com.devflowteam.data.remote.TMDBApiService
import com.devflowteam.data.repository.MovieDetailRepositoryImpl
import com.devflowteam.domain.repository.MovieDetailRepository
import com.devflowteam.domain.util.Time
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "https://api.themoviedb.org/3/trending/"
private const val MOVIE_DB_NAME = "movie.db"
private const val MOVIE_DETAIL_DB_NAME = "moviedetail.db"

@OptIn(ExperimentalPagingApi::class)
val dataModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.BEARER_TOKEN}")
                    .build()
                chain.proceed(request)
            }
        }.build()
    }

    single<TMDBApiService> {
        val json = Json { ignoreUnknownKeys = true }
        val client = get<OkHttpClient>()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
            .create(TMDBApiService::class.java)
    }

    single<Pager<Int, MovieEntity>>(named(Time.DAY)) {
        val movieDb: MovieDatabase = get()

        Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = TrendingMoviesRemoteMediator(
                api = get(),
                database = movieDb,
                time = Time.DAY
            ),
            pagingSourceFactory = {
                movieDb.movieDao().pagingSource()
            }
        )
    }

    single<Pager<Int, MovieEntity>>(named(Time.WEEK)) {
        val movieDb: MovieDatabase = get()

        Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = TrendingMoviesRemoteMediator(
                api = get(),
                database = movieDb,
                time = Time.WEEK
            ),
            pagingSourceFactory = {
                movieDb.movieDao().pagingSource()
            }
        )
    }

    single<MovieDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = MovieDatabase::class.java,
            name = MOVIE_DB_NAME
        ).build()
    }

    single<MovieDetailDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = MovieDetailDatabase::class.java,
            name = MOVIE_DETAIL_DB_NAME
        ).build()
    }

    single<MovieDetailRepository> {
        val db: MovieDetailDatabase = get()

        MovieDetailRepositoryImpl(
            dao = db.dao()
        )
    }
}