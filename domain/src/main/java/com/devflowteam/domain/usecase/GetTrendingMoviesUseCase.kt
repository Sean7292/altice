package com.devflowteam.domain.usecase

import com.devflowteam.domain.repository.TMDBApiServiceRepository
import com.devflowteam.domain.util.Time

class GetTrendingMoviesUseCase(
    private val repository: TMDBApiServiceRepository
) {
    suspend operator fun invoke(time: Time) = repository.getTrendingMovies(time.type)
}