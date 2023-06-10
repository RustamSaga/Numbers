package com.rustamsaga.numbers.main.sl

import androidx.lifecycle.ViewModel

/**
 * @author Asatryan on 30.09.2022
 */
interface Module<T : ViewModel> {

    fun viewModel(): T
}