package com.devflowteam.data.remote

import com.devflowteam.data.remote.dto.MovieDetailDto
import com.devflowteam.data.remote.dto.TrendingMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApiService {

    @GET("trending/movie/{time}")
    suspend fun getTrendingMovies(
        @Path("time") time: String
    ): Response<TrendingMoviesDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Long
    ): Response<MovieDetailDto>
}