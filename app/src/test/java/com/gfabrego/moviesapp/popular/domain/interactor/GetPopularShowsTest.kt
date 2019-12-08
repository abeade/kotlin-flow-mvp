package com.gfabrego.moviesapp.popular.domain.interactor

import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.model.Show
import com.gfabrego.moviesapp.popular.domain.repository.PopularShowsRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.net.URL
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class GetPopularShowsTest {

    private val testScope = TestCoroutineScope()

    @Mock
    private lateinit var popularShowsRepository: PopularShowsRepository

    @Test
    fun `build should call repository`() {
        val shows = anyListOfShows()
        val request = PageRequest.Paged(1)
        given(popularShowsRepository.getPopularShows(request)).willReturn(flowOf(PopularShowsResponse(shows, PageRequest.Paged(2), -1)))

        buildUseCase().build(GetPopularShows.Params(request))

        verify(popularShowsRepository).getPopularShows(request)
    }

    @Test
    fun `build should return expected flow`() = testScope.runBlockingTest {
        val shows = anyListOfShows()
        val request = PageRequest.Paged(1)
        val flow = flowOf(PopularShowsResponse(shows, PageRequest.Paged(2), -1))
        given(popularShowsRepository.getPopularShows(request)).willReturn(flow)

        val result = buildUseCase().build(GetPopularShows.Params(request)).single()

        assertEquals(PopularShowsResponse(shows, PageRequest.Paged(2), 2), result)
    }

    private fun anyListOfShows() =
        listOf(Show("1", "TITLE1", URL("http://test1.com")), Show("2", "TITLE2", URL("http://test2.com")))

    private fun buildUseCase() = GetPopularShows(popularShowsRepository)
}
