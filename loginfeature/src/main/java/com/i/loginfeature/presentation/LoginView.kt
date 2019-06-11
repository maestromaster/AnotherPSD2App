package com.i.loginfeature.presentation

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun setNetworkError()
    fun setOtherError()
    fun navigateToHome()
    fun showStatusText()
    fun hideStatusText()
}