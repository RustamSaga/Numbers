package com.rustamsaga.numbers.details.presentation

import androidx.lifecycle.ViewModel
import com.rustamsaga.numbers.details.data.NumberFactDetails

class NumberDetailsViewModel(
    private val data: NumberFactDetails.Read
) : ViewModel(), NumberFactDetails.Read {

    override fun read(): String = data.read()
}