package com.rustamsaga.numbers.main.sl

import androidx.lifecycle.ViewModel
import com.rustamsaga.numbers.details.presentation.NumberDetailsViewModel
import com.rustamsaga.numbers.details.sl.NumberDetailsModule
import com.rustamsaga.numbers.main.presentation.MainViewModel
import com.rustamsaga.numbers.numbers.domain.NumbersRepository
import com.rustamsaga.numbers.numbers.presentation.NumbersViewModel
import com.rustamsaga.numbers.numbers.sl.NumbersModule
import com.rustamsaga.numbers.numbers.sl.ProvideNumbersRepository


interface DependencyContainer {

    fun <T : ViewModel> module(clasz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> =
            throw IllegalStateException("no module found for $clasz")
    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ) : DependencyContainer, ProvideNumbersRepository {

        private val repository: NumbersRepository by lazy {
            ProvideNumbersRepository.Base(core).provideNumbersRepository()
        }

        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> = when (clasz) {
            MainViewModel::class.java -> MainModule(core)
            NumbersViewModel.Base::class.java -> NumbersModule(core, this)
            NumberDetailsViewModel::class.java -> NumberDetailsModule(core)
            else -> dependencyContainer.module(clasz)
        }

        override fun provideNumbersRepository(): NumbersRepository = repository
    }
}