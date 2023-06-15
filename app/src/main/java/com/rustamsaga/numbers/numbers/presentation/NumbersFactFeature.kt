package com.rustamsaga.numbers.numbers.presentation

import com.rustamsaga.numbers.R
import com.rustamsaga.numbers.main.presentation.Handle
import com.rustamsaga.numbers.main.presentation.UiFeature
import com.rustamsaga.numbers.numbers.domain.NumbersFactUseCase
import com.rustamsaga.numbers.numbers.domain.NumbersResult


interface NumbersFactFeature : UiFeature, suspend () -> NumbersResult {

    fun number(number: String): UiFeature

    class Base(
        private val manageResources: ManageResources,
        communications: NumbersCommunications,
        numbersResultMapper: NumbersResult.Mapper<Unit>,
        private val useCase: NumbersFactUseCase
    ) : NumbersFeature(communications, numbersResultMapper), NumbersFactFeature {

        private var number: String = "0"

        override fun number(number: String): UiFeature {
            this.number = number
            return this
        }

        override fun handle(handle: Handle) = if (number.isEmpty())
            handle.handle({
                NumbersResult.Failure(
                    manageResources.string(R.string.empty_number_error_message)
                )
            }) { showUi(it) }
        else
            super.handle(handle)

        override suspend fun invoke() = useCase.factAboutNumber(number)
    }
}