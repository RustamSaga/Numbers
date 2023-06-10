package com.rustamsaga.numbers.numbers.domain

/**
 * Отвечает за получение данных из разных источников
 */
interface NumbersRepository : RandomNumberRepository {

    suspend fun allNumbers(): List<NumberFact>

    suspend fun numberFact(number: String): NumberFact
}

interface RandomNumberRepository {
    suspend fun randomNumberFact(): NumberFact
}