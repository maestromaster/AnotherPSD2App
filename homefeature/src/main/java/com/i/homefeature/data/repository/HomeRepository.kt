package com.i.homefeature.data.repository

import com.i.homefeature.domain.HomeRepositoryContract
import com.i.security.model.UserData
import com.i.security.model.accountInformation.BalancesInformation
import com.i.security.service.AccountInformationServiceContract
import io.reactivex.Observable
import retrofit2.Response

class HomeRepository(val accountInformationService: AccountInformationServiceContract) : HomeRepositoryContract {

    override fun loadFromNetwork(data: UserData): Observable<Response<BalancesInformation>> {
        return accountInformationService.requestAccountInformation(data)
    }
}