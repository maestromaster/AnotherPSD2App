package com.i.loginfeature.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.View.INVISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.i.feature.FeaturePresenterContract
import com.i.homefeature.presentation.HomeActivity
import com.i.loginfeature.R
import com.i.loginfeature.domain.LoginPresenterContract
import com.i.security.model.AuthenticationData
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter: LoginPresenterContract by inject {parametersOf(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        oAuthButton.setOnClickListener { initiateLogin() }
    }

    private fun initiateLogin(){
        presenter.fetch(AuthenticationData())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showProgress() {
        progress.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = INVISIBLE
    }

    override fun setUsernameError() {
        statusText.text = getString(R.string.error_username_empty)
    }

    override fun setPasswordError() {
        statusText.text = getString(R.string.error_username_password)
    }

    override fun setNetworkError() {
        statusText.text = getString(R.string.error_no_network)
    }

    override fun setOtherError() {
        statusText.text = getString(R.string.error_unknown_error)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun showStatusText() {
        statusText.visibility = VISIBLE
    }

    override fun hideStatusText() {
        statusText.visibility = GONE
    }
}
