package com.i.apiclient.service

import com.i.apiclient.model.AuthenticationData
import com.i.apiclient.model.oauth.OauthData
import io.reactivex.Observable
import retrofit2.Response

interface OauthServiceContract {

    fun requestOauth(authenticationData: AuthenticationData): Observable<Response<OauthData>>

}