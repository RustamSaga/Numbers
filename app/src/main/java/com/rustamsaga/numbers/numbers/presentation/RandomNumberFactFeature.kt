package com.rustamsaga.numbers.numbers.presentation

import com.rustamsaga.numbers.numbers.domain.NumbersResult
import com.rustamsaga.numbers.numbers.domain.RandomNumbersFactUseCase

class RandomNumberFactFeature(
    private val useCase: RandomNumbersFactUseCase,
    communications: NumbersCommunications,
    numbersResultMapper: NumbersResult.Mapper<Unit>,
) : NumbersFeature(communications, numbersResultMapper) {

    override suspend fun invoke() = useCase.factAboutRandomNumber()
}