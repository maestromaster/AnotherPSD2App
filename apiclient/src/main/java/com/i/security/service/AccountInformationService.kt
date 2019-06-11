package com.i.security.service

import com.i.security.datasource.AccountDatasource
import com.i.security.model.UserData
import com.i.security.model.accountInformation.BalancesInformation
import io.reactivex.Observable
import retrofit2.Response

class AccountInformationService(val accountDatasource: AccountDatasource) : AccountInformationServiceContract {

    override fun requestAccountInformation(userData: UserData): Observable<Response<BalancesInformation>> {
        return accountDatasource.balances(userData.accountId)
    }


}