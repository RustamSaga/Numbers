package com.rustamsaga.numbers.main.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface NumbersCommunications : ObserveNumber {
    fun showProgress(show: Boolean)

    fun showState(uiState: UiState)

    fun showList(list: List<NumberUi>)

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>)
    override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)
    override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>)

    class Base(
        private val progressCommunication: ProgressCommunication,
        private val numbersStateCommunication: NumbersStateCommunication,
        private val numbersListCommunication: NumbersListCommunication
    ): NumbersCommunications {
        override fun showProgress(show: Boolean) = progressCommunication.map(show)

        override fun showState(uiState: UiState) = numbersStateCommunication.map(uiState)

        override fun showList(list: List<NumberUi>) = numbersListCommunication.map(list)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) {
            progressCommunication.observe(owner, observer)
        }

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) {
            numbersStateCommunication.observe(owner, observer)
        }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) {
            numbersListCommunication.observe(owner, observer)
        }
    }

}

interface ObserveNumber {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>)
    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>)
}

interface ProgressCommunication: Communication.Mutable<Boolean> {

    class Base(): Communication.Post<Boolean>(), ProgressCommunication
}

interface NumbersStateCommunication: Communication.Mutable<UiState> {

    class Base(): Communication.Post<UiState>(), NumbersStateCommunication
}
interface NumbersListCommunication: Communication.Mutable<List<NumberUi>> {

    class Base(): Communication.Post<List<NumberUi>> (), NumbersListCommunication
}