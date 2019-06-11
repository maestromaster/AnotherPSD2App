package com.i.homefeature.domain

import com.i.feature.FeatureRepositoryContract
import com.i.security.model.accountInformation.BalancesInformation
import com.i.security.model.UserData

interface HomeRepositoryContract : FeatureRepositoryContract<UserData, BalancesInformation>