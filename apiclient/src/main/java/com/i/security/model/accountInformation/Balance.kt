package com.i.security.model.accountInformation

data class Balance(val balanceType: String,
                   val balanceAmount: BalanceAmount,
                   val lastChangeDateTime: String,
                   val referenceDate: String
)