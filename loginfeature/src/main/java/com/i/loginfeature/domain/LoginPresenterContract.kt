package com.i.loginfeature.domain

import com.i.feature.FeaturePresenterContract
import com.i.feature.RepositoryCallback
import com.i.apiclient.model.AuthenticationData
import com.i.apiclient.model.oauth.OauthData

interface LoginPresenterContract : FeaturePresenterContract<AuthenticationData>, RepositoryCallback<OauthData> {
    fun authenticate()
}