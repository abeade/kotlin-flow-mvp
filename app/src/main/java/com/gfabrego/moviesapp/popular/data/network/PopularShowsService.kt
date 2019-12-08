package com.gfabrego.moviesapp.popular.data.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PopularShowsService {

    @GET(PATH_POPULAR_SHOWS)
    fun getPopularShows(@Query(PARAM_API_KEY) apiKey: String, @Query(PARAM_PAGE) page: Int): Flow<PopularShowsApiResponse>

    private companion object {
        private const val PATH_POPULAR_SHOWS = "/3/tv/popular"
        private const val PARAM_API_KEY = "api_key"
        private const val PARAM_PAGE = "page"
    }
}
