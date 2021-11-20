package com.example.investment.application

import com.example.investment.application.dto.request.InvestmentRequest
import com.example.investment.application.dto.response.InvestmentResponse

interface InvestmentApplication {
    fun invest(accountId: Long, investmentRequest: InvestmentRequest): InvestmentResponse
}