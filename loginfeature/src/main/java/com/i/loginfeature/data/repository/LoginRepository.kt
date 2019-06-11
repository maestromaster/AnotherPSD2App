package com.i.loginfeature.data.repository

import com.i.apiclient.model.AuthenticationData
import com.i.apiclient.model.oauth.OauthData
import com.i.apiclient.service.OauthServiceContract
import io.reactivex.Observable
import retrofit2.Response

class LoginRepository(val loginService: OauthServiceContract) : LoginRepositoryContract {

    override fun loadFromNetwork(data: AuthenticationData): Observable<Response<OauthData>> {
        return loginService.requestOauth(data)
    }

}