package com.rustamsaga.numbers.numbers.domain


abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException : DomainException()