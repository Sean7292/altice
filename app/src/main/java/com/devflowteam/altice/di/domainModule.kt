package com.devflowteam.altice.di

import com.devflowteam.domain.usecase.MovieDetailDeleteUseCase
import com.devflowteam.domain.usecase.MovieDetailGetAllMovieDetailIdsUseCase
import com.devflowteam.domain.usecase.MovieDetailGetAllMovieDetailsUseCase
import com.devflowteam.domain.usecase.MovieDetailUpsertUseCase
import org.koin.dsl.module

val domainModule = module {

    single<MovieDetailUpsertUseCase> {
        MovieDetailUpsertUseCase(
            repository = get()
        )
    }

    single<MovieDetailDeleteUseCase> {
        MovieDetailDeleteUseCase(
            repository = get()
        )
    }

    single<MovieDetailGetAllMovieDetailsUseCase> {
        MovieDetailGetAllMovieDetailsUseCase(
            repository = get()
        )
    }

    single<MovieDetailGetAllMovieDetailIdsUseCase> {
        MovieDetailGetAllMovieDetailIdsUseCase(
            repository = get()
        )
    }
}