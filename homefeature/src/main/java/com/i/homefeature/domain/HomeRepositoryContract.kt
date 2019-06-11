package com.i.homefeature.domain

import com.i.feature.FeatureRepositoryContract
import com.i.apiclient.model.accountInformation.BalancesInformation
import com.i.apiclient.model.UserData

interface HomeRepositoryContract : FeatureRepositoryContract<UserData, BalancesInformation>