package com.i.anotherpsd2app

import com.i.anotherpsd2app.di.appModules
import com.i.anotherpsd2app.di.featureModules
import com.i.apiclient.model.AuthenticationData
import com.i.loginfeature.data.repository.LoginRepositoryContract
import com.i.loginfeature.domain.LoginPresenter
import com.i.loginfeature.domain.LoginPresenterContract
import com.i.loginfeature.presentation.LoginView
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock
import org.mockito.Mockito
import org.mockito.Mockito.mock

class LoginPresenterTest : KoinTest {

    private val view: LoginView = mock()
    private val repository: LoginRepositoryContract by inject()
    private val presenter: LoginPresenterContract by inject { parametersOf(view) }

    @Before
    fun before() {
        startKoin(listOf(appModules, featureModules))
        declareMock<LoginRepositoryContract>()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `check that authenticate invokes a repository callback`() {
        val authData = AuthenticationData(content = "content")

        presenter.fetch(authData)

        Mockito.verify(repository).loadFromNetwork(data = eq(authData))
    }

}
