package com.gfabrego.moviesapp.popular.data.network

import com.gfabrego.moviesapp.BuildConfig
import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class PopularShowsApiDataSource(
    private val service: PopularShowsService,
    private val apiShowsMapper: PopularShowsApiMapper
) {

    fun getPopularShows(pageRequest: PageRequest.Paged): Flow<PopularShowsResponse> =
        flow { emit(service.getPopularShows(BuildConfig.API_KEY, pageRequest.page)) }
            .map(apiShowsMapper::map)

//    fun getPopularShows(pageRequest: PageRequest.Paged): Flow<PopularShowsResponse> {
//
//        suspend fun remoteCall(): PopularShowsApiResponse =
//            service.getPopularShows(BuildConfig.API_KEY, pageRequest.page)
//
//        return ::remoteCall.asFlow().map(apiShowsMapper::map)
//    }
}
