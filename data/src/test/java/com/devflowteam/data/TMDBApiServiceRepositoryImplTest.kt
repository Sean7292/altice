package com.devflowteam.data

import com.devflowteam.data.remote.TMDBApiService
import com.devflowteam.data.remote.dto.BelongsToCollectionDto
import com.devflowteam.data.remote.dto.MovieDetailDto
import com.devflowteam.data.remote.dto.MovieDto
import com.devflowteam.data.remote.dto.TrendingMoviesDto
import com.devflowteam.data.repository.TMDBApiServiceRepositoryImpl
import com.devflowteam.data.util.toDomain
import com.devflowteam.domain.repository.TMDBApiServiceRepository
import com.devflowteam.domain.usecase.GetMovieDetailUseCase
import com.devflowteam.domain.usecase.GetTrendingMoviesUseCase
import com.devflowteam.domain.util.Time
import com.devflowteam.domain.util.error.DataError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import com.devflowteam.domain.util.error.Result
import junit.framework.TestCase.assertEquals
import okhttp3.ResponseBody
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class TMDBApiServiceRepositoryImplTest {

    private lateinit var repository: TMDBApiServiceRepository
    private val apiService = mockk<TMDBApiService>()

    private val trendingMoviesDto = TrendingMoviesDto(
        page = 1,
        movies = listOf(
            MovieDto(
                adult = false,
                backdropPath = "/a6bItEVaxgphpMswho1wVRerv4r.jpg",
                genreIds = listOf(
                    28,
                    12,
                    53,
                    80
                ),
                id = 7451,
                originalLanguage = "en",
                originalTitle = "xXx",
                overview = "Xander Cage is your standard adrenaline junkie with no fear and a lousy attitude. When the US Government \\\"recruits\\\" him to go on a mission, he's not exactly thrilled. His mission: to gather information on an organization that may just be planning the destruction of the world, led by the nihilistic Yorgi.",
                popularity = 126.8124,
                posterPath = "/xeEw3eLeSFmJgXZzmF2Efww0q3s.jpg",
                releaseDate = "2002-08-09",
                title = "xXx",
                video = false,
                voteAverage = 5.957,
                voteCount = 4497
            ),
            MovieDto(
                adult = false,
                backdropPath = "/fQOV47FHTJdaSuSUNlzP3zXUZWE.jpg",
                genreIds = listOf(
                    878,
                    12,
                    28
                ),
                id = 1234821,
                originalLanguage = "en",
                originalTitle = "Jurassic World Rebirth",
                overview = "Five years after the events of Jurassic World Dominion, covert operations expert Zora Bennett is contracted to lead a skilled team on a top-secret mission to secure genetic material from the world's three most massive dinosaurs. When Zora's operation intersects with a civilian family whose boating expedition was capsized, they all find themselves stranded on an island where they come face-to-face with a sinister, shocking discovery that's been hidden from the world for decades.",
                popularity = 672.6363,
                posterPath = "/1RICxzeoNCAO5NpcRMIgg1XT6fm.jpg",
                releaseDate = "2025-07-01",
                title = "Jurassic World Rebirth",
                video = false,
                voteAverage = 6.374,
                voteCount = 1001
            )
        ),
        totalPages = 10,
        totalResults = 100
    )
    private val trendingMovies = trendingMoviesDto.toDomain()
    
    private val movieDetailDto = MovieDetailDto(
        adult = false,
        backdropPath = "backdrop.jpg",
        belongsToCollection = BelongsToCollectionDto(
            id = 2,
            name = "Collection",
            posterPath = "poster.jpg",
            backdropPath = "backdrop.jpg"
        ),
        budget = 100000,
        genres = emptyList(),
        homepage = "",
        id = 1,
        imdbId = "tt1234567",
        originCountry = emptyList(),
        originalLanguage = "en",
        originalTitle = "Original Title",
        overview = "Movie overview",
        popularity = 8.0,
        posterPath = "poster.jpg",
        productionCompanies = emptyList(),
        productionCountries = emptyList(),
        releaseDate = "2023-01-01",
        revenue = 500000,
        runtime = 120,
        spokenLanguages = emptyList(),
        status = "Released",
        tagline = "Tagline",
        title = "Movie Title",
        video = false,
        voteAverage = 7.5,
        voteCount = 100
    )
    private val movieDetail = movieDetailDto.toDomain()

    @Before
    fun setup() {
        repository = TMDBApiServiceRepositoryImpl(apiService)
    }

    @Test
    fun `getTrendingMovies returns Success when response is successful`() = runTest {
        coEvery { apiService.getTrendingMovies("day") } returns Response.success(trendingMoviesDto)

        val result = repository.getTrendingMovies("day")

        assert(result is Result.Success)
        assertEquals(trendingMovies, (result as Result.Success).data)
    }

    @Test
    fun `getTrendingMovies returns SERVER error when response is not successful`() = runTest {
        val errorResponse = Response.error<TrendingMoviesDto>(500, ResponseBody.create(null, ""))
        coEvery { apiService.getTrendingMovies("day") } returns errorResponse

        val result = repository.getTrendingMovies("day")

        assert(result is Result.Error && result.error == DataError.Network.SERVER)
    }

    @Test
    fun `getTrendingMovies returns NO_INTERNET on IOException`() = runTest {
        coEvery { apiService.getTrendingMovies("day") } throws IOException()

        val result = repository.getTrendingMovies("day")

        assert(result is Result.Error && result.error == DataError.Network.NO_INTERNET)
    }

    @Test(expected = CancellationException::class)
    fun `getTrendingMovies throws CancellationException`() = runTest {
        coEvery { apiService.getTrendingMovies("day") } throws CancellationException()

        repository.getTrendingMovies("day")
    }

    @Test
    fun `getTrendingMovies returns UNKNOWN on other Exception`() = runTest {
        coEvery { apiService.getTrendingMovies("day") } throws IllegalStateException()

        val result = repository.getTrendingMovies("day")

        assert(result is Result.Error && result.error == DataError.Network.UNKNOWN)
    }

    @Test
    fun `getMovieDetail returns Success when response is successful`() = runTest {
        coEvery { apiService.getMovieDetail(1) } returns Response.success(movieDetailDto)

        val result = repository.getMovieDetail(1)

        assert(result is Result.Success)
        assertEquals(movieDetail, (result as Result.Success).data)
    }

    @Test
    fun `getMovieDetail returns SERVER error when response is not successful`() = runTest {
        val errorResponse = Response.error<MovieDetailDto>(500, ResponseBody.create(null, ""))
        coEvery { apiService.getMovieDetail(1) } returns errorResponse

        val result = repository.getMovieDetail(1)

        assert(result is Result.Error && result.error == DataError.Network.SERVER)
    }

    @Test
    fun `getMovieDetail returns NO_INTERNET on IOException`() = runTest {
        coEvery { apiService.getMovieDetail(1) } throws IOException()

        val result = repository.getMovieDetail(1)

        assert(result is Result.Error && result.error == DataError.Network.NO_INTERNET)
    }

    @Test(expected = CancellationException::class)
    fun `getMovieDetail throws CancellationException`() = runTest {
        coEvery { apiService.getMovieDetail(1) } throws CancellationException()

        repository.getMovieDetail(1)
    }

    @Test
    fun `getMovieDetail returns UNKNOWN on other Exception`() = runTest {
        coEvery { apiService.getMovieDetail(1) } throws IllegalStateException()

        val result = repository.getMovieDetail(1)

        assert(result is Result.Error && result.error == DataError.Network.UNKNOWN)
    }

    private val mockRepo = mockk<TMDBApiServiceRepository>()

    @Test
    fun `GetTrendingMoviesUseCase returns success`() = runTest {
        val useCase = GetTrendingMoviesUseCase(mockRepo)
        coEvery { mockRepo.getTrendingMovies(Time.WEEK.type) } returns Result.Success(trendingMovies)

        val result = useCase(Time.WEEK)
        assert(result is Result.Success && result.data == trendingMovies)
    }

    @Test
    fun `GetTrendingMoviesUseCase returns error`() = runTest {
        val useCase = GetTrendingMoviesUseCase(mockRepo)
        coEvery { mockRepo.getTrendingMovies(Time.WEEK.type) } returns Result.Error(DataError.Network.SERVER)

        val result = useCase(Time.WEEK)
        assert(result is Result.Error && result.error == DataError.Network.SERVER)
    }

    @Test
    fun `GetMovieDetailUseCase returns success`() = runTest {
        val useCase = GetMovieDetailUseCase(mockRepo)
        coEvery { mockRepo.getMovieDetail(1L) } returns Result.Success(movieDetail)

        val result = useCase(1L)
        assert(result is Result.Success && result.data == movieDetail)
    }

    @Test
    fun `GetMovieDetailUseCase returns error`() = runTest {
        val useCase = GetMovieDetailUseCase(mockRepo)
        coEvery { mockRepo.getMovieDetail(1L) } returns Result.Error(DataError.Network.UNKNOWN)

        val result = useCase(1L)
        assert(result is Result.Error && result.error == DataError.Network.UNKNOWN)
    }
}