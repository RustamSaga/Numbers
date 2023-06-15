package com.rustamsaga.numbers.numbers.domain

import com.rustamsaga.numbers.details.data.NumberFactDetails

/**
 * NameInteractor - это сборник useCase'ов (который используется в viewmodel когда код стал большим)
 * Это бизнес логика - оно не само получает данные а делает это через посредника,
 * однако этот интерфейс отвечает за логику получения данных (кому можно, кому нельзя - типа премиум и обычный)
 */
interface NumbersInteractor :
    NumbersInitialUseCase,
    NumbersFactUseCase,
    RandomNumbersFactUseCase,
    NumberDetailsUseCase {

    class Base(
        private val repository: NumbersRepository,
        private val handleRequest: HandleRequest,
        private val numberFactDetails: NumberFactDetails.Save
    ) : NumbersInteractor {

        override fun saveDetails(details: String) = numberFactDetails.save(details)

        override suspend fun init() = NumbersResult.Success(repository.allNumbers())

        override suspend fun factAboutNumber(number: String) = handleRequest.handle {
            repository.numberFact(number)
        }

        override suspend fun factAboutRandomNumber() = handleRequest.handle {
            repository.randomNumberFact()
        }
    }
}

interface NumbersInitialUseCase {
    suspend fun init(): NumbersResult
}

interface NumbersFactUseCase {
    suspend fun factAboutNumber(number: String): NumbersResult
}

interface RandomNumbersFactUseCase {
    suspend fun factAboutRandomNumber(): NumbersResult
}

interface NumberDetailsUseCase {
    fun saveDetails(details: String)
}