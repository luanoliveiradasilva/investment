package com.example.investment.service.facade.impl

import com.example.investment.infrastructure.AccountClient
import com.example.investment.service.facade.AccountFacade
import com.example.investment.service.facade.dto.DebitAccountRequest
import com.example.investment.service.facade.valueObject.AccountBalanceVO
import com.example.investment.service.facade.valueObject.DebitAccountVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AccountFacadeImpl: AccountFacade {

    @Autowired
    lateinit var accountClient: AccountClient


    override fun getAccountBalanceById(accountId: Long): AccountBalanceVO {
        var accountBalanceVO: AccountBalanceVO = accountClient.accountBalance(accountId)
        return accountBalanceVO
    }

    override fun debitAccount(accountId: Long, valueOfInvestment: Double): Boolean {
        var debitAccountVO: DebitAccountVO = accountClient.debit(accountId, DebitAccountRequest(valueOfInvestment))

        return debitAccountVO.debited

    }


}