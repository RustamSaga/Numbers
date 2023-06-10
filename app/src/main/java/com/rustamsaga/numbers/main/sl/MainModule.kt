package com.rustamsaga.numbers.main.sl

import com.rustamsaga.numbers.main.presentation.MainViewModel
import com.rustamsaga.numbers.main.sl.Module


class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        core.provideWorkManagerWrapper(),
        core.provideNavigation()
    )
}