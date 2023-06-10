package com.rustamsaga.numbers.numbers.data

import com.rustamsaga.numbers.numbers.domain.HandleError
import com.rustamsaga.numbers.numbers.domain.NoInternetConnectionException
import com.rustamsaga.numbers.numbers.domain.ServiceUnavailableException
import java.net.UnknownHostException

class HandleDomainError : HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}