package com.i.apiclient.datasource

import com.i.apiclient.model.accountInformation.BalancesInformation
import com.i.apiclient.model.oauth.OauthData
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MockDatasource {

    @GET("/maestromaster/AnotherPSD2App/master/mockedRequests/balances.json")
    fun balances(@Query("address") accountId: String): Observable<Response<BalancesInformation>>

    @GET("/maestromaster/AnotherPSD2App/master/mockedRequests/oauth.json")
    fun oauth(): Observable<Response<OauthData>>

}