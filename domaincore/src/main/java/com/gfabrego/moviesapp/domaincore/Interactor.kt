package com.gfabrego.moviesapp.domaincore

import io.reactivex.Observable

interface Interactor<I: Any, O: Any> {

    fun build(params: I): Observable<O>
}
