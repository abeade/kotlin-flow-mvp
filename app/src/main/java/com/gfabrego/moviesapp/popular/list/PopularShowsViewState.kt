package com.gfabrego.moviesapp.popular.list

import com.gfabrego.moviesapp.popular.domain.model.PageRequest
import com.gfabrego.moviesapp.popular.domain.model.Show

internal class PopularShowsViewState(val listState: ListState) {

    internal sealed class ListState {

        object LoadingShows: ListState()
        data class DisplayingShows(val showsList: List<Show>, val nextPage: PageRequest?): ListState()
        data class LoadingMoreShows(val showsList: List<Show>, val nextPage: PageRequest?): ListState()
        object NoResults: ListState()
        data class Error(val throwable: Throwable): ListState()
    }
}
