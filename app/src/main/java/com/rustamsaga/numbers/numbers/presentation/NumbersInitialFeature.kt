package com.rustamsaga.numbers.numbers.presentation

import com.rustamsaga.numbers.numbers.domain.NumbersInitialUseCase
import com.rustamsaga.numbers.numbers.domain.NumbersResult


class NumbersInitialFeature(
    communications: NumbersCommunications,
    numbersResultMapper: NumbersResult.Mapper<Unit>,
    private val useCase: NumbersInitialUseCase
) : NumbersFeature(communications, numbersResultMapper) {

    override suspend fun invoke() = useCase.init()
}