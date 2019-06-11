package com.i.homefeature.data.repository

import com.i.homefeature.domain.HomeRepositoryContract
import com.i.apiclient.model.UserData
import com.i.apiclient.model.accountInformation.BalancesInformation
import com.i.apiclient.service.AccountInformationServiceContract
import io.reactivex.Observable
import retrofit2.Response

class HomeRepository(val accountInformationService: AccountInformationServiceContract) : HomeRepositoryContract {

    override fun loadFromNetwork(data: UserData): Observable<Response<BalancesInformation>> {
        return accountInformationService.requestAccountInformation(data)
    }
}