package com.gfabrego.moviesapp.popular.domain.repository

import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import io.reactivex.Single

internal interface PopularShowsRepository {

    fun getPopularShows(pageRequest: PageRequest): Single<PopularShowsResponse>
}
