package com.i.apiclient.service

import com.i.apiclient.model.UserData
import com.i.apiclient.model.accountInformation.BalancesInformation
import io.reactivex.Observable
import retrofit2.Response

interface AccountInformationServiceContract {

    fun requestAccountInformation(userData: UserData): Observable<Response<BalancesInformation>>

}