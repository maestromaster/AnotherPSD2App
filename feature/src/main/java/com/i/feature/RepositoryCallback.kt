package com.i.feature

import com.i.errormanager.FeatureError

interface RepositoryCallback<P> {
    fun onSuccess(data: P)
    fun onError(error: FeatureError)
}