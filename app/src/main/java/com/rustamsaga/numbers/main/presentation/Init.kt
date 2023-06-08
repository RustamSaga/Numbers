package com.rustamsaga.numbers.main.presentation

/**
 *  Проверяет, включалось ли это приложение до этого или нет
 */
interface Init {
    fun init(isFirstRun: Boolean)
}