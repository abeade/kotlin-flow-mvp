package com.gfabrego.moviesapp.popular.domain.repository

import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.PopularShowsResponse
import kotlinx.coroutines.flow.Flow

internal interface PopularShowsRepository {

    fun getPopularShows(pageRequest: PageRequest): Flow<PopularShowsResponse>
}
