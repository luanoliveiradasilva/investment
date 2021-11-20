package com.example.investment.application.adapter

import com.example.investment.application.dto.response.InvestmentResponse
import com.example.investment.model.Investment

class InvestmentAdapter {

    fun toDtoInvestment(investment: Investment): InvestmentResponse {
        return InvestmentResponse(investment.id, investment.value, investment.date)
    }
}