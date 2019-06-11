package com.i.feature

interface FeaturePresenterContract<T> {
    fun fetch(data: T)
    fun destroy()
}