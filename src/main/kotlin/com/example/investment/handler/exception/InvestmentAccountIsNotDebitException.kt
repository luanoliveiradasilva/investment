package com.example.investment.handler.exception

class InvestmentAccountIsNotDebitException: RuntimeException {

    var description: String=""
        get() {return  description}

    constructor(message: String, description: String) {
        super.message
        this.description = description
    }
}