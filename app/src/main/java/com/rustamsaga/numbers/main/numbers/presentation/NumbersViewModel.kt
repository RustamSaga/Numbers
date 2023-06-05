package com.rustamsaga.numbers.main.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.rustamsaga.numbers.main.numbers.domain.NumberFact
import com.rustamsaga.numbers.main.numbers.domain.NumberUiMapper
import com.rustamsaga.numbers.main.numbers.domain.NumbersInteractor
import com.rustamsaga.numbers.main.numbers.domain.NumbersResult

class NumbersViewModel(
    private val communications: NumbersCommunications,
    private val interactor: NumbersInteractor,
    private val numberResultMapper: NumbersResult.Mapper<Unit>
) : FetchNumbers, ObserveNumber {
    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communications.observeProgress(owner, observer)
    }

    override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) {
        communications.observeState(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) {
        communications.observeList(owner, observer)
    }

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            communications.showProgress(true)
            viewModelScope.launch {
                val result = interactor.init()
                result.map(numberResultMapper)
            }
        }
    }

    override fun fetchRandomNumberFact() {
        TODO("Not yet implemented")
    }

    override fun fetchNumberFact(number: String) {
        TODO("Not yet implemented")
    }

}

interface FetchNumbers {

    fun init(isFirstRun: Boolean)

    fun fetchRandomNumberFact()

    fun fetchNumberFact(number: String)
}
