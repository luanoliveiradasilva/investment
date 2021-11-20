package com.example.investment.service.facade

import com.example.investment.service.facade.valueObject.AccountBalanceVO

interface AccountFacade {

    fun getAccountBalanceById(accountId: Long): AccountBalanceVO

    fun debitAccount(accountId: Long, valueOfInvestment: Double): Boolean
}