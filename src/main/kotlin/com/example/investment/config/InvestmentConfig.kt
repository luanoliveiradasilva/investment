package com.example.investment.config

import feign.Logger
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InvestmentConfig {
    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun client(): OkHttpClient {
        return OkHttpClient()
    }
}