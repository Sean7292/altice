package com.devflowteam.domain.usecase

import com.devflowteam.domain.repository.TMDBApiServiceRepository

class GetMovieDetailUseCase(
    private val repository: TMDBApiServiceRepository
) {
    suspend operator fun invoke(id: Long) = repository.getMovieDetail(id)
}