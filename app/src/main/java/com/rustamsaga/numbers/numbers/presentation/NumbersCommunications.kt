package com.rustamsaga.numbers.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * Этот интерфейс обертка над liveData - кторый может получить данные и подписываться
 * Может подписываться т.к наследуется от ObserveNumbers
 */
interface NumbersCommunications : ObserveNumbers {

    fun showProgress(show: Int)

    fun showState(uiState: UiState)

    fun showList(list: List<NumberUi>)

    class Base(
        private val progress: ProgressCommunication,
        private val state: NumbersStateCommunication,
        private val numbersList: NumbersListCommunication
    ) : NumbersCommunications {

        override fun showProgress(show: Int) = progress.map(show)

        override fun showState(uiState: UiState) = state.map(uiState)

        override fun showList(list: List<NumberUi>) = numbersList.map(list)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            progress.observe(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            state.observe(owner, observer)

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) =
            numbersList.observe(owner, observer)
    }
}

/**
 * Обертка над liveData - умеет только подписываться
 */
interface ObserveNumbers {

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)

    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>)

    fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>)
}

/**
 * LiveData для хранения Int
 */
interface ProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Ui<Int>(), ProgressCommunication
}

/**
 * LiveData для хранения UiState
 */
interface NumbersStateCommunication : Communication.Mutable<UiState> {
    class Base : Communication.Ui<UiState>(), NumbersStateCommunication
}

/**
 * LiveData для хранения List<NumberUi>
 */
interface NumbersListCommunication : Communication.Mutable<List<NumberUi>> {
    class Base : Communication.Ui<List<NumberUi>>(), NumbersListCommunication
}