package com.devflowteam.domain.usecase

import com.devflowteam.domain.repository.MovieDetailRepository

class MovieDetailGetAllMovieDetailsUseCase(
    private val repository: MovieDetailRepository
) {
    operator fun invoke() = repository.getAllMovieDetails()
}