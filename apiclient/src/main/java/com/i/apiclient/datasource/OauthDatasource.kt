package com.i.apiclient.datasource

import com.i.apiclient.model.AuthenticationData
import com.i.apiclient.model.oauth.OauthData
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.POST

interface OauthDatasource {

    @POST("/oauth2/token")
    fun oauth(authenticationData: AuthenticationData): Observable<Response<OauthData>>

}