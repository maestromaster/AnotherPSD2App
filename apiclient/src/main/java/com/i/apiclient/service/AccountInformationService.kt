package com.i.apiclient.service

import com.i.apiclient.datasource.MockDatasource
import com.i.apiclient.model.UserData
import com.i.apiclient.model.accountInformation.BalancesInformation
import io.reactivex.Observable
import retrofit2.Response

class AccountInformationService(val accountDatasource: MockDatasource) : AccountInformationServiceContract {

    override fun requestAccountInformation(userData: UserData): Observable<Response<BalancesInformation>> {
        return accountDatasource.balances(userData.accountId)
    }


}