package com.gfabrego.moviesapp.domaincore

import kotlinx.coroutines.flow.Flow

interface Interactor<I: Any, O: Any> {

    fun build(params: I): Flow<O>
}
