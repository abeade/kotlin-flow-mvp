package com.gfabrego.moviesapp.popular.data.network

import com.gfabrego.moviesapp.BuildConfig
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import io.reactivex.Single

internal class PopularShowsApiDataSource(
    private val service: PopularShowsService,
    private val apiShowsMapper: PopularShowsApiMapper
) {

    fun getPopularShows(pageRequest: PageRequest.Paged): Single<PopularShowsResponse> =
        service.getPopularShows(BuildConfig.API_KEY, pageRequest.page).map(apiShowsMapper::map)
}
