package com.rustamsaga.numbers.numbers.presentation

import com.rustamsaga.numbers.numbers.domain.NumberFact
import com.rustamsaga.numbers.numbers.domain.NumbersResult


class NumbersResultMapper(
    private val communications: NumbersCommunications,
    private val mapper: NumberFact.Mapper<NumberUi>
) : NumbersResult.Mapper<Unit> {

    override fun map(list: List<NumberFact>) {
        if (list.isNotEmpty())
            communications.showList(list.map { it.map(mapper) })
        communications.showState(UiState.Success())
    }

    override fun map(errorMessage: String) =
        communications.showState(UiState.ShowError(errorMessage))
}