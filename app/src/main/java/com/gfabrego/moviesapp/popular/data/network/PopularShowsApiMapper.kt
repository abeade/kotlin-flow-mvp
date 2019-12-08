package com.gfabrego.moviesapp.popular.data.network

import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import com.gfabrego.moviesapp.popular.domain.model.Show
import java.net.URL

internal class PopularShowsApiMapper {

    internal suspend fun map(response: PopularShowsApiResponse): PopularShowsResponse =
        PopularShowsResponse(mapShows(response), mapNextPage(response), -1)

    private fun mapShows(response: PopularShowsApiResponse): List<Show> =
        response.popularShows.asSequence().mapNotNull(::mapShow).toList()

    private fun mapShow(popularShowApi: PopularShowsApiResponse.PopularShowApi): Show? =
        try {
            with(popularShowApi) {
                Show(
                    id = id.toString(),
                    title = name,
                    poster = URL("$BASE_POSTER_IMAGE_URL$posterPath")
                )
            }
        } catch (throwable: Throwable) {
            // TODO: logging
            null
        }

    private fun mapNextPage(response: PopularShowsApiResponse): PageRequest? =
        when {
            response.totalPages < response.page -> PageRequest.Paged(response.page + 1)
            else -> null
        }

    private companion object {
        // TODO: configurations might be retrieved from the configuration API
        private const val BASE_POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w154"
    }
}
