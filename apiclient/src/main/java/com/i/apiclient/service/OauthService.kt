package com.i.apiclient.service

import com.i.apiclient.datasource.MockDatasource
import com.i.apiclient.model.AuthenticationData
import com.i.apiclient.model.oauth.OauthData
import io.reactivex.Observable
import retrofit2.Response

class OauthService(val oauthDatasource: MockDatasource) : OauthServiceContract {

    override fun requestOauth(authenticationData: AuthenticationData): Observable<Response<OauthData>> {
        return oauthDatasource.oauth()
    }
}