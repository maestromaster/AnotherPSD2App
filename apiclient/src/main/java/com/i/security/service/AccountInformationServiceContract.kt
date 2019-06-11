package com.i.security.service

import com.i.security.model.UserData
import com.i.security.model.accountInformation.BalancesInformation
import io.reactivex.Observable
import retrofit2.Response

interface AccountInformationServiceContract {

    fun requestAccountInformation(userData: UserData): Observable<Response<BalancesInformation>>

}