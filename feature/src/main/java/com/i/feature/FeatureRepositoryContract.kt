package com.i.feature

import io.reactivex.Observable
import retrofit2.Response

interface FeatureRepositoryContract<T, P> {
    fun loadFromNetwork(data: T): Observable<Response<P>>
}