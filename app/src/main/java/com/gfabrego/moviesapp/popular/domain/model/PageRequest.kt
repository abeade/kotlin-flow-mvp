package com.gfabrego.moviesapp.popular.domain.model

internal sealed class PageRequest {

    data class Paged(val page: Int): PageRequest()
}

internal class PageRequestFactory {

    private companion object {
        private const val INITIAL_PAGE = 1
    }

    fun createInitialPage(): PageRequest = PageRequest.Paged(INITIAL_PAGE)
}
