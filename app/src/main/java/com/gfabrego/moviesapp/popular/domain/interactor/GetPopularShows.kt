package com.gfabrego.moviesapp.popular.domain.interactor

import com.gfabrego.moviesapp.domaincore.Interactor
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.repository.PopularShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GetPopularShows(private val popularShowsRepository: PopularShowsRepository) :
    Interactor<GetPopularShows.Params, PopularShowsResponse> {

    override fun build(params: Params): Flow<PopularShowsResponse> =
        popularShowsRepository.getPopularShows(params.pageRequest)
            .map { it.copy(size = it.shows.size) }

    data class Params(val pageRequest: PageRequest)
}
