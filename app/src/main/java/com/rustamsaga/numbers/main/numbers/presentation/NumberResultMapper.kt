package com.rustamsaga.numbers.main.numbers.presentation

import com.rustamsaga.numbers.main.numbers.domain.NumberFact
import com.rustamsaga.numbers.main.numbers.domain.NumbersResult

class NumberResultMapper(
    private val communications: NumbersCommunications,
    private val mapper: NumberFact.Mapper<NumberUi>
)  : NumbersResult.Mapper<Unit> {
    override fun map(list: List<NumberFact>, errorMessage: String) {
        communications.showState(
            if (errorMessage.isEmpty()){
                communications.showList(list.map { it.map(mapper) })
                UiState.Success()
            }
            else
                UiState.Error(errorMessage)
        )
    }

}