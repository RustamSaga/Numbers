package com.rustamsaga.numbers.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustamsaga.numbers.numbers.presentation.DispatchersList
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO what does it do???
abstract class BaseViewModel(
    private val dispatchersList: DispatchersList
) : ViewModel(), Handle {

    override fun <T : Any> handle(
        block: suspend () -> T,
        ui: (T) -> Unit
    ) = viewModelScope.launch(dispatchersList.io()) {
        val result = block.invoke()
        withContext(dispatchersList.ui()) {
            ui.invoke(result)
        }
    }
}
// TODO what does it do???
interface Handle {
    fun <T : Any> handle(
        block: suspend () -> T,
        ui: (T) -> Unit
    ): Job
}