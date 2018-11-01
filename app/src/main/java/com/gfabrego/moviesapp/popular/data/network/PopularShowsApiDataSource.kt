package com.gfabrego.moviesapp.popular.data.network

import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import io.reactivex.Single

internal class PopularShowsApiDataSource(
    private val service: PopularShowsService,
    private val apiShowsMapper: PopularShowsApiMapper
) {

    fun getPopularShows(pageRequest: PageRequest.Paged): Single<PopularShowsResponse> =
        service.getPopularShows(API_KEY, pageRequest.page).map(apiShowsMapper::map)

    private companion object {

        // TODO: move this somewhere when injection done
        private const val API_KEY = "aaa"
    }
}
