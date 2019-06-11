package com.i.security.datasource

import com.i.security.model.accountInformation.BalancesInformation
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountDatasource {

    @GET("/maestromaster/AnotherPSD2App/master/mockedRequests/balances.json")
    fun balances(@Query("address") accountId: String): Observable<Response<BalancesInformation>>

}