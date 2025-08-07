package com.devflowteam.domain.usecase

import com.devflowteam.domain.model.MovieDetail
import com.devflowteam.domain.repository.MovieDetailRepository

class MovieDetailDeleteUseCase(
    private val repository: MovieDetailRepository
) {
    suspend operator fun invoke(movieDetail: MovieDetail) = repository.delete(movieDetail)
}