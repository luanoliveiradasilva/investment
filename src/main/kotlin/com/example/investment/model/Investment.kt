package com.example.investment.model

import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
@Data
class Investment() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var productId: Long = 0

    var accountId: Long = 0

    var value: Double = 0.0

    @CreationTimestamp
    lateinit var date: Date

    var privateInvestment = false

    constructor(productId: Long, accountId: Long, value: Double) : this() {
        this.productId = productId
        this.accountId = accountId
        this.value = value
    }

    fun sufficientBalanceForInvestment(accountBalance: Double): Boolean {
        return value!! < accountBalance
    }

    fun verifyProductPrivateOrDefaultForInvestment(accountBalance: Double, product: Product): Boolean {
        if (!product.privateInvestment) {
            privateInvestment = false
            return true
        }
        if (product.privateInvestment && accountBalance >= product.minimumValueForInvestment) {
            privateInvestment = true
            return true
        }
        return false
    }

}