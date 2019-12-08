package com.gfabrego.moviesapp.popular.list

import com.gfabrego.moviesapp.popular.domain.model.Show

internal interface PopularShowsView {

    //region VIEW RENDERING
    fun hideLoading()

    fun showError()

    fun showLoading()

    fun showNoResults()

    fun showShows(showsList: List<Show>)
    //endregion
}
