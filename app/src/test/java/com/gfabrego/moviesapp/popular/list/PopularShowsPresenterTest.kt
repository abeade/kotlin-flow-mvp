package com.gfabrego.moviesapp.popular.list

import com.gfabrego.moviesapp.domaincore.Interactor
import com.gfabrego.moviesapp.popular.domain.interactor.GetPopularShows
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PageRequestFactory
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.model.Show
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.net.URL

@RunWith(MockitoJUnitRunner::class)
class PopularShowsPresenterTest {

    private val testScope = TestCoroutineScope()

    @Mock
    private lateinit var view: PopularShowsView

    @Mock
    private lateinit var getPopularShows: Interactor<GetPopularShows.Params, PopularShowsResponse>

    @Mock
    private lateinit var pageRequestFactory: PageRequestFactory

    @Test
    fun `initial load should emit shows response`() = testScope.runBlockingTest {
        val shows = anyListOfShows()
        val request = PageRequest.Paged(1)
        given(pageRequestFactory.createInitialPage()).willReturn(request)
        given(getPopularShows.build(GetPopularShows.Params(request))).willReturn(flow { emit(PopularShowsResponse(shows, PageRequest.Paged(2), -1)) })

        buildPresenter().attachView()

        verify(view).showShows(shows)
    }

    private fun anyListOfShows() =
        listOf(Show("1", "TITLE1", URL("http://test1.com")), Show("2", "TITLE2", URL("http://test2.com")))

    private fun buildPresenter() =
        PopularShowsPresenter(
            view,
            getPopularShows,
            pageRequestFactory,
            testScope
        )
}
