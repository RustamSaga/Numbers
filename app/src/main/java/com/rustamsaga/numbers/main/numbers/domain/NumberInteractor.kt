package com.rustamsaga.numbers.main.numbers.domain

interface NumbersInteractor {

    fun saveDetails(details: String)

    suspend fun init(): NumbersResult

    suspend fun factAboutNumber(number: String): NumbersResult

    suspend fun factAboutRandomNumber(): NumbersResult
}