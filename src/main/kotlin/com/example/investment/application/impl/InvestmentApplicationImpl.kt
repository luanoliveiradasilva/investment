package com.example.investment.application.impl


import com.example.investment.application.InvestmentApplication
import com.example.investment.application.adapter.InvestmentAdapter
import com.example.investment.application.dto.request.InvestmentRequest
import com.example.investment.application.dto.response.InvestmentResponse
import com.example.investment.model.Investment
import com.example.investment.service.InvestmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InvestmentApplicationImpl: InvestmentApplication {

    @Autowired
    lateinit var investmentService: InvestmentService

    @Autowired
    lateinit var investmentAdapter: InvestmentAdapter

    override fun invest(accountId: Long, investmentRequest: InvestmentRequest): InvestmentResponse {
        val investment: Investment = investmentService.investment(
            investmentRequest.productId, accountId,
            investmentRequest.value
        )

        return investmentAdapter.toDtoInvestment(investment)
    }


}