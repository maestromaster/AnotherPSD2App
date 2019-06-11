package com.i.loginfeature.data.repository

import com.i.feature.FeatureRepositoryContract
import com.i.apiclient.model.AuthenticationData
import com.i.apiclient.model.oauth.OauthData

interface LoginRepositoryContract : FeatureRepositoryContract<AuthenticationData, OauthData>