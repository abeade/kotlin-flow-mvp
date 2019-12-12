package com.gfabrego.moviesapp.popular.list

import com.gfabrego.moviesapp.domaincore.Interactor
import com.gfabrego.moviesapp.popular.domain.interactor.GetPopularShows
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PageRequestFactory
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.model.Show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.net.URL

@RunWith(MockitoJUnitRunner::class)
class PopularShowsPresenterTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var view: PopularShowsView

    @Mock
    private lateinit var getPopularShows: Interactor<GetPopularShows.Params, PopularShowsResponse>

    @Mock
    private lateinit var pageRequestFactory: PageRequestFactory

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `initial load should emit shows response`() {
        val shows = anyListOfShows()
        val request = PageRequest.Paged(1)
        given(pageRequestFactory.createInitialPage()).willReturn(request)
        given(getPopularShows.build(GetPopularShows.Params(request))).willReturn(flowOf(PopularShowsResponse(shows, PageRequest.Paged(2), -1)))

        buildPresenter().attachView()
        testDispatcher.advanceTimeBy(5000)

        verify(view).showShows(shows)
    }

    private fun anyListOfShows() =
        listOf(Show("1", "TITLE1", URL("http://test1.com")), Show("2", "TITLE2", URL("http://test2.com")))

    private fun buildPresenter() =
        PopularShowsPresenter(
            view,
            getPopularShows,
            pageRequestFactory,
            MainScope()
        )
}
