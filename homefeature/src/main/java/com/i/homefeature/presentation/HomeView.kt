package com.i.homefeature.presentation

import com.i.apiclient.model.accountInformation.BalancesInformation

interface HomeView {
    fun showProgress()
    fun hideProgress()
    fun navigateToLogin()
    fun setOtherError()
    fun hideError()
    fun showResult(data: BalancesInformation)
    fun hideResult()
}