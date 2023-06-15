package com.rustamsaga.numbers.numbers.data.cloud

import com.rustamsaga.numbers.numbers.data.NumberData
import com.rustamsaga.numbers.numbers.data.cache.FetchNumber


interface NumbersCloudDataSource : FetchNumber {

    suspend fun randomNumber(): NumberData

    class Base(
        private val service: NumbersService,
        private val randomApiHeader: RandomApiHeader
    ) : NumbersCloudDataSource {

        override suspend fun randomNumber(): NumberData {
            val response = service.random()
            val body = response.body() ?: throw IllegalStateException("service unavailable")
            val headers = response.headers()
            randomApiHeader.findInHeaders(headers)?.let { (_, value) ->
                return NumberData(value, body)
            }
            throw IllegalStateException("service unavailable")
        }

        override suspend fun number(number: String): NumberData {
            val fact = service.fact(number)
            return NumberData(number, fact)
        }
    }
}