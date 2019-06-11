package com.i.homefeature.domain

import com.i.errormanager.FeatureError.*
import com.i.errormanager.FeatureError
import com.i.errormanager.FeatureError.AUTHENTICATION_ERROR
import com.i.feature.RepositoryCallback
import com.i.homefeature.presentation.HomeView
import com.i.security.model.accountInformation.BalancesInformation
import com.i.security.model.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.HttpException

class HomePresenter(var homeView: HomeView?) :
    HomePresenterContract, RepositoryCallback<BalancesInformation>, KoinComponent {

    private val homeRepository: HomeRepositoryContract by inject()

    private var disposable: Disposable? = null

    override fun fetch(data: UserData) {
        homeView?.apply {
            showProgress()
            hideError()
            hideResult()
        }
        disposable = homeRepository.loadFromNetwork(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.body()?.let { balanceData ->
                    onSuccess(balanceData)
                } ?: run { onError(EMPTY_BODY) }
            }, {error ->
                if (error is HttpException) {
                    onError(HTTP_ERROR)
                } else {
                    onError(NO_NETWORK)
                }
            })
    }

    override fun onSuccess(data: BalancesInformation) {
       homeView?.apply {
           hideProgress()
           showResult(data)
       }
    }

    override fun onError(error: FeatureError) {
        homeView?.apply {
            hideProgress()
            when(error) {
                AUTHENTICATION_ERROR -> navigateToLogin()
                else -> setOtherError()
            }
        }
    }

    override fun destroy() {
        homeView = null
        disposable?.dispose()
    }

}