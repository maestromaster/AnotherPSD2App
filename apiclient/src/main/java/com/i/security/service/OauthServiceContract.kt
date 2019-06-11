package com.i.security.service

import com.i.security.model.AuthenticationData
import com.i.security.model.oauth.OauthData
import io.reactivex.Observable
import retrofit2.Response

interface OauthServiceContract {

    fun requestOauth(authenticationData: AuthenticationData): Observable<Response<OauthData>>

}