package com.example.investment.handler.exception

import java.util.*

data class ErrorMessageResponse (var statusCode: Int,
                                    var timestamp: Date,
                                    var message: String,
                                    var description: String){

}