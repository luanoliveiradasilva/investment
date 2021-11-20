package com.example.investment.api


import com.example.investment.application.InvestmentApplication
import com.example.investment.application.dto.request.InvestmentRequest
import com.example.investment.application.dto.response.InvestmentResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

class InvestmentController {
    @Autowired
    lateinit var investmentApplication: InvestmentApplication

    @PostMapping("/{accountId}/investment")
    fun invest(
        @PathVariable accountId: Long,
        @RequestBody investmentRequest: InvestmentRequest
    ): ResponseEntity<InvestmentResponse> {
        val investmentResponse: InvestmentResponse = investmentApplication.invest(accountId, investmentRequest)
        return ResponseEntity.ok(investmentResponse)
    }
}