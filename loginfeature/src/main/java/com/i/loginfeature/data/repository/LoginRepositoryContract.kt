package com.i.loginfeature.data.repository

import com.i.feature.FeatureRepositoryContract
import com.i.security.model.AuthenticationData
import com.i.security.model.oauth.OauthData

interface LoginRepositoryContract : FeatureRepositoryContract<AuthenticationData, OauthData>