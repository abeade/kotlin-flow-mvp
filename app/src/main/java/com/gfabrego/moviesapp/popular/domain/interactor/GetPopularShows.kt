package com.gfabrego.moviesapp.popular.domain.interactor

import com.gfabrego.moviesapp.domaincore.Interactor
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.repository.PopularShowsRepository
import io.reactivex.Observable

internal class GetPopularShows(private val popularShowsRepository: PopularShowsRepository) :
    Interactor<GetPopularShows.Params, PopularShowsResponse> {

    override fun build(params: Params): Observable<PopularShowsResponse> =
        popularShowsRepository.getPopularShows(params.pageRequest).toObservable()

    data class Params(val pageRequest: PageRequest)
}
