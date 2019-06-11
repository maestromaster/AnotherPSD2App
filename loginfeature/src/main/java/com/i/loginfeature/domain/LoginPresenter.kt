package com.i.loginfeature.domain

import com.i.errormanager.FeatureError
import com.i.errormanager.FeatureError.*
import com.i.loginfeature.data.repository.LoginRepositoryContract
import com.i.loginfeature.presentation.LoginView
import com.i.apiclient.SecurityHelper
import com.i.apiclient.model.AuthenticationData
import com.i.apiclient.model.oauth.OauthData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.HttpException
import java.util.*

class LoginPresenter(var loginView: LoginView?) :
    LoginPresenterContract, KoinComponent {

    private val loginRepository: LoginRepositoryContract by inject()
    private val securityHelper: SecurityHelper by inject()
    private var disposable: Disposable? = null

    override fun authenticate() {
        loginView?.apply {
            showProgress()
            hideStatusText()
        }

        // TODO: Move to service
        val content = "grant_type=client_credentials&scope=greetings%3Aview"
        val reqId = UUID.randomUUID().toString()
        val digest = securityHelper.createDigest(content)
        val date = Date().toString()
        val toSign = "(request-target): POST /oauth2/token\ndate: %s\ndigest: %s\nx-ing-reqid: %s"
            .format(date, digest, reqId)
        val signature = securityHelper.createSignature(toSign, securityHelper.readPrivateKeyFile())
        val authorization = "Signature keyId=e77d776b-90af-4684-bebc-521e5b2614dd,algorithm=\"rsa-sha256\",headers=\"(request-target) date digest x-ing-reqid\",signature=\"$signature\""
        val authenticationData = AuthenticationData(content = content, xIngReqId = reqId, digest = digest, date = date, authorization = authorization)

        disposable = loginRepository.loadFromNetwork(authenticationData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.body()?.let { oAuthData ->
                    onSuccess(oAuthData)
                } ?: run { onError(EMPTY_BODY) }
            }, {error ->
                if (error is HttpException) {
                    onError(HTTP_ERROR)
                } else {
                    onError(NO_NETWORK)
                }
            })
    }

    override fun fetch(data: AuthenticationData) {
        disposable = loginRepository.loadFromNetwork(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.body()?.let { oAuthData ->
                    onSuccess(oAuthData)
                } ?: run { onError(EMPTY_BODY) }
            }, {error ->
                if (error is HttpException) {
                    onError(HTTP_ERROR)
                } else {
                    onError(NO_NETWORK)
                }
            })
    }

    override fun destroy() {
        loginView = null
        disposable?.dispose()
    }

    override fun onSuccess(data: OauthData) {
        loginView?.apply {
            hideProgress()
            navigateToHome()
        }
    }

    override fun onError(error: FeatureError) {
        loginView?.apply {
            hideProgress()
            showStatusText()
            when(error) {
                USERNAME_IS_EMPTY -> setUsernameError()
                PASSWORD_IS_EMPTY -> setPasswordError()
                NO_NETWORK -> setNetworkError()
                else -> setOtherError()
            }
        }
    }

}