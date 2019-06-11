package com.i.loginfeature.data.repository

import com.i.security.model.AuthenticationData
import com.i.security.model.oauth.OauthData
import com.i.security.service.OauthServiceContract
import io.reactivex.Observable
import retrofit2.Response

class LoginRepository(val oauthService: OauthServiceContract) : LoginRepositoryContract {

    override fun loadFromNetwork(data: AuthenticationData): Observable<Response<OauthData>> {
        return oauthService.requestOauth(data)
    }

}