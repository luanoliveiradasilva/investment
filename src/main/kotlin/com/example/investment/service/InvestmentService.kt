package com.example.investment.service

import com.example.investment.model.Investment

interface InvestmentService {

    fun investment( productId: Long,  accountId: Long, valueInvestment: Double): Investment

}