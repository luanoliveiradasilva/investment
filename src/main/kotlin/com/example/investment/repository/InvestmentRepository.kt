package com.example.investment.repository

import com.example.investment.model.Investment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvestmentRepository: JpaRepository<Investment, Long> {
}