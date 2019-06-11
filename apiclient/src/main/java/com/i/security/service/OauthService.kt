package com.i.security.service

import com.i.security.datasource.AccountDatasource
import com.i.security.datasource.OauthDatasource
import com.i.security.model.AuthenticationData
import com.i.security.model.UserData
import com.i.security.model.accountInformation.BalancesInformation
import com.i.security.model.oauth.OauthData
import io.reactivex.Observable
import retrofit2.Response

class OauthService(val oauthDatasource: OauthDatasource) : OauthServiceContract {

    override fun requestOauth(authenticationData: AuthenticationData): Observable<Response<OauthData>> {
        return oauthDatasource.oAuth(authenticationData)
    }
}