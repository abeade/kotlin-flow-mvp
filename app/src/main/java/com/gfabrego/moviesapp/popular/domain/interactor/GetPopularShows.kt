package com.gfabrego.moviesapp.popular.domain.interactor

import com.gfabrego.moviesapp.domaincore.Interactor
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.repository.PopularShowsRepository
import kotlinx.coroutines.flow.Flow

internal class GetPopularShows(private val popularShowsRepository: PopularShowsRepository) :
    Interactor<GetPopularShows.Params, PopularShowsResponse> {

    override fun build(params: Params): Flow<PopularShowsResponse> =
        popularShowsRepository.getPopularShows(params.pageRequest)

    data class Params(val pageRequest: PageRequest)
}
