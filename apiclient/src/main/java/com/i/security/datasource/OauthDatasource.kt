package com.i.security.datasource

import com.i.security.model.AuthenticationData
import com.i.security.model.oauth.OauthData
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST

interface OauthDatasource {
    @POST("/oauth2/token")
    fun oAuth(authenticationData: AuthenticationData): Observable<Response<OauthData>>
}