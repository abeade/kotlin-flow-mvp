package com.gfabrego.moviesapp.popular.data.network

import com.gfabrego.moviesapp.BuildConfig
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

internal class PopularShowsApiDataSource(
    private val service: PopularShowsService,
    private val apiShowsMapper: PopularShowsApiMapper
) {

    fun getPopularShows(pageRequest: PageRequest.Paged): Flow<PopularShowsResponse> =
        service.getPopularShows(BuildConfig.API_KEY, pageRequest.page)
            .transform { value ->
                delay(5000)
                return@transform emit(value)
            }
            .map(apiShowsMapper::map)
}
