package com.devflowteam.domain.usecase

import com.devflowteam.domain.repository.MovieDetailRepository

class MovieDetailGetAllMovieDetailIdsUseCase(
    private val repository: MovieDetailRepository
) {
    operator fun invoke() = repository.getAllMovieDetailIds()
}