package com.gfabrego.moviesapp.popular.domain.model

internal data class PopularShowsResponse(val shows: List<Show>, val nextPage: PageRequest?)
