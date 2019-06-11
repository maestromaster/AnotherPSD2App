package com.i.loginfeature.domain

import com.i.feature.FeaturePresenterContract
import com.i.feature.RepositoryCallback
import com.i.security.model.AuthenticationData
import com.i.security.model.oauth.OauthData

interface LoginPresenterContract : FeaturePresenterContract<AuthenticationData>, RepositoryCallback<OauthData> {
    fun authenticate()
}