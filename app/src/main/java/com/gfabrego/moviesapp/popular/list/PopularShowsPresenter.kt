package com.gfabrego.moviesapp.popular.list

import com.gfabrego.moviesapp.domaincore.Interactor
import com.gfabrego.moviesapp.popular.domain.interactor.GetPopularShows
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PageRequestFactory
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

internal class PopularShowsPresenter(
    private val view: PopularShowsView,
    private val getPopularShows: Interactor<GetPopularShows.Params, PopularShowsResponse>,
    private val pageRequestFactory: PageRequestFactory
) {

    private val disposables = CompositeDisposable()

    internal fun attachView() {
        val firstPageLoad = view
            .loadFirstPageIntent()
            .flatMap { getPopularShows.build(GetPopularShows.Params(buildInitialPage())) }
            .map { response ->
                PopularShowsViewState(
                    PopularShowsViewState.ListState.DisplayingShows(response.shows, response.nextPage)
                )
            }
            .onErrorResumeNext { throwable: Throwable ->
                Observable.just(
                    PopularShowsViewState(PopularShowsViewState.ListState.Error(throwable))
                )
            }
            .startWith(PopularShowsViewState(PopularShowsViewState.ListState.LoadingShows))

//        fun getObservable() =
//            lifecycleObserver.lifecycleSubject
//                .filter { it == Lifecycle.Event.ON_CREATE }
//                .switchMap { firstPageLoad }
//                .observeOn(AndroidSchedulers.mainThread())
//                .takeUntil(lifecycleObserver.lifecycleSubject.filter { it == Lifecycle.Event.ON_DESTROY })

        disposables.add(
            firstPageLoad
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { viewState -> renderViewState(viewState) })
    }

    private fun renderViewState(viewState: PopularShowsViewState) =
        when (viewState.listState) {
            PopularShowsViewState.ListState.LoadingShows -> view.showLoading()
            PopularShowsViewState.ListState.NoResults -> renderNoResultsState()
            is PopularShowsViewState.ListState.Error -> renderErrorState()
            is PopularShowsViewState.ListState.DisplayingShows -> renderDisplayShowsState(viewState.listState)
            is PopularShowsViewState.ListState.LoadingMoreShows -> TODO()
        }

    private fun renderNoResultsState() {
        view.hideLoading()
        view.showNoResults()
    }

    private fun renderErrorState() {
        view.hideLoading()
        view.showError()
    }

    private fun renderDisplayShowsState(listState: PopularShowsViewState.ListState.DisplayingShows) {
        view.hideLoading()
        view.showShows(listState.showsList)
    }

    private fun buildInitialPage(): PageRequest = pageRequestFactory.createInitialPage()

    fun detachView() {
        disposables.clear()
    }
}
