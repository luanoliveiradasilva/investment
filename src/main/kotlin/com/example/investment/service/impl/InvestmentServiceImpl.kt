package com.example.investment.service.impl

import com.example.investment.handler.exception.InvestmentAccountIsNotDebitException
import com.example.investment.handler.exception.InvestmentAccountWithoutBalanceException
import com.example.investment.handler.exception.InvestmentAccountWithoutBalanceForProductPrivateException
import com.example.investment.handler.exception.InvestmentProductDontExistException
import com.example.investment.model.Investment
import com.example.investment.model.Product
import com.example.investment.repository.InvestmentRepository
import com.example.investment.repository.ProductRepository
import com.example.investment.service.InvestmentService
import com.example.investment.service.facade.AccountFacade
import com.example.investment.service.facade.valueObject.AccountBalanceVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*


@Service
class InvestmentServiceImpl: InvestmentService {


    @Value("\${lab.investment.exceptions.product-dont-exists-message}")
    val messageExceptionProductDontExists: String = ""

    @Value("\${lab.investment.exceptions.product-dont-exists-description}")
    val descriptionExceptionProductDontExists: String = ""

    @Value("\${lab.investment.exceptions.account-without-balance-message}")
    val messageExceptionAccountWithoutBalance: String = ""

    @Value("\${lab.investment.exceptions.account-without-balance-description}")
    val descriptionExceptionAccountWithoutBalance: String = ""

    @Value("\${lab.investment.exceptions.account-without-balance-for-product-private-message}")
    val messageExceptionAccountWithoutBalanceForProductPrivate: String = ""

    @Value("\${lab.investment.exceptions.account-without-balance-for-product-private-description}")
    val descriptionExceptionAccountWithoutBalanceForProductPrivate: String = ""

    @Value("\${lab.investment.exceptions.account-is-not-debited-message}")
    val messageExceptionAccountIsNotDebited: String = ""

    @Value("\${lab.investment.exceptions.account-is-not-debited-description}")
    val descriptionExceptionAccountIsNotDebited: String = ""

    @Autowired
    lateinit var investmentRepository: InvestmentRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var accountFacade: AccountFacade

    override fun investment(productId: Long, accountId: Long, valueInvestment: Double): Investment {
        val product:Optional<Product> = productRepository.findById(productId)

        if (product.isEmpty)
            throw InvestmentProductDontExistException(
                messageExceptionProductDontExists,
                descriptionExceptionProductDontExists
        )

        val investment = Investment(productId, accountId, valueInvestment)

        val accountBalanceVO: AccountBalanceVO = accountFacade.getAccountBalanceById(accountId)

        if (!investment.sufficientBalanceForInvestment(accountBalanceVO.balance))
            throw InvestmentAccountWithoutBalanceException(
                messageExceptionAccountWithoutBalance,
                descriptionExceptionAccountWithoutBalance
        )

        if (!investment.verifyProductPrivateOrDefaultForInvestment(
                accountBalanceVO.balance,
                product.get()
            )
        ) throw InvestmentAccountWithoutBalanceForProductPrivateException(
            messageExceptionAccountWithoutBalanceForProductPrivate,
            descriptionExceptionAccountWithoutBalanceForProductPrivate
        )

        val isDebited = accountFacade.debitAccount(accountId, valueInvestment)

        if (!isDebited) throw InvestmentAccountIsNotDebitException(
            messageExceptionAccountIsNotDebited,
            descriptionExceptionAccountIsNotDebited
        )

        investmentRepository.save(investment)

        return investment
    }
}