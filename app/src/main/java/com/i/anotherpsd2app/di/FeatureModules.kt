package com.i.anotherpsd2app.di

import com.i.homefeature.data.repository.HomeRepository
import com.i.homefeature.domain.HomePresenter
import com.i.homefeature.domain.HomePresenterContract
import com.i.homefeature.domain.HomeRepositoryContract
import com.i.homefeature.presentation.HomeView
import com.i.loginfeature.data.repository.LoginRepository
import com.i.loginfeature.data.repository.LoginRepositoryContract
import com.i.loginfeature.domain.LoginPresenter
import com.i.loginfeature.domain.LoginPresenterContract
import com.i.loginfeature.presentation.LoginView
import com.i.apiclient.service.AccountInformationService
import com.i.apiclient.service.AccountInformationServiceContract
import com.i.apiclient.service.OauthService
import com.i.apiclient.service.OauthServiceContract
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val featureModules : Module = module {
    // Login
    factory<OauthServiceContract> { OauthService(oauthDatasource = get()) }

    factory<LoginPresenterContract> { (view: LoginView) -> LoginPresenter(view) }

    factory<LoginRepositoryContract> { LoginRepository(loginService = get()) }

    // Home
    factory<AccountInformationServiceContract> { AccountInformationService(accountDatasource = get()) }

    factory<HomeRepositoryContract> { HomeRepository(accountInformationService = get()) }

    factory<HomePresenterContract> { (view: HomeView) -> HomePresenter(view) }
}