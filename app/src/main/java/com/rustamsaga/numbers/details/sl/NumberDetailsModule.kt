package com.rustamsaga.numbers.details.sl

import com.rustamsaga.numbers.details.presentation.NumberDetailsViewModel
import com.rustamsaga.numbers.main.sl.Module
import com.rustamsaga.numbers.main.sl.ProvideNumberDetails

class NumberDetailsModule(
    private val provideNumberDetails: ProvideNumberDetails
) : Module<NumberDetailsViewModel> {

    override fun viewModel() = NumberDetailsViewModel(provideNumberDetails.provideNumberDetails())
}