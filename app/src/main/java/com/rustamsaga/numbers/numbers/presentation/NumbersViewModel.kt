package com.rustamsaga.numbers.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.rustamsaga.numbers.main.presentation.*
import com.rustamsaga.numbers.numbers.domain.NumberDetailsUseCase

interface NumbersViewModel : Init, FetchNumbers, ObserveNumbers, ClearError, ShowDetails {

    class Base(
        dispatchersList: DispatchersList,
        private val initial: UiFeature,
        private val numberFact: NumbersFactFeature,
        private val randomNumberFact: UiFeature,
        private val showDetails: ShowDetails,
        private val communications: NumbersCommunications
    ) : BaseViewModel(dispatchersList), NumbersViewModel {

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            communications.observeProgress(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            communications.observeState(owner, observer)

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) =
            communications.observeList(owner, observer)

        override fun init(isFirstRun: Boolean) {
            if (isFirstRun) initial.handle(this)
        }

        override fun fetchRandomNumberFact() {
            randomNumberFact.handle(this)
        }

        override fun fetchNumberFact(number: String) {
            numberFact.number(number).handle(this)
        }

        override fun clearError() = communications.showState(UiState.ClearError())

        override fun showDetails(item: NumberUi) = showDetails.showDetails(item)
    }
}

interface ShowDetails {

    fun showDetails(item: NumberUi)

    class Base(
        private val useCase: NumberDetailsUseCase,
        private val navigationCommunication: NavigationCommunication.Mutate,
        private val detailsMapper: NumberUi.Mapper<String>,
    ) : ShowDetails {

        override fun showDetails(item: NumberUi) {
            useCase.saveDetails(item.map(detailsMapper))
            navigationCommunication.map(NavigationStrategy.Add(Screen.Details))
        }
    }
}

interface FetchNumbers {

    fun fetchRandomNumberFact()

    fun fetchNumberFact(number: String)
}

interface ClearError {
    fun clearError()
}