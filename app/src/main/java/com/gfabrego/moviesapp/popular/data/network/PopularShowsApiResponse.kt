package com.gfabrego.moviesapp.popular.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PopularShowsApiResponse(
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val popularShows: List<PopularShowApi>
) {

    @JsonClass(generateAdapter = true)
    internal data class PopularShowApi(
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "overview")
        val overview: String,
        @Json(name = "poster_path")
        val posterPath: String,
        @Json(name = "vote_average")
        val voteAverage: Float,
        @Json(name = "backdrop_path")
        val backdropPath: String
    )
}
