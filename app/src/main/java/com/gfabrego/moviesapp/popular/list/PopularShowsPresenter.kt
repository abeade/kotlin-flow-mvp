package com.gfabrego.moviesapp.popular.list

import com.gfabrego.moviesapp.domaincore.Interactor
import com.gfabrego.moviesapp.popular.domain.interactor.GetPopularShows
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PageRequestFactory
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.model.Show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class PopularShowsPresenter(
    private val view: PopularShowsView,
    private val getPopularShows: Interactor<GetPopularShows.Params, PopularShowsResponse>,
    private val pageRequestFactory: PageRequestFactory,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    internal suspend fun attachView() {
        view.showLoading()
        withContext(coroutineContext) {
            getPopularShows.build(GetPopularShows.Params(buildInitialPage()))
                .catch { renderErrorState() }
                .single()
                .let {
                    if (it.shows.isEmpty()) {
                        renderNoResultsState()
                    } else {
                        renderDisplayShowsState(it.shows)
                    }
                }
        }
    }

    private fun renderNoResultsState() {
        view.hideLoading()
        view.showNoResults()
    }

    private fun renderErrorState() {
        view.hideLoading()
        view.showError()
    }

    private fun renderDisplayShowsState(showsList: List<Show>) {
        view.hideLoading()
        view.showShows(showsList)
    }

    private fun buildInitialPage(): PageRequest = pageRequestFactory.createInitialPage()
}
