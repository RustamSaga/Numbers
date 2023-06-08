package com.rustamsaga.numbers.main.presentation

import com.rustamsaga.numbers.details.presentation.NumberDetailsFragment
import com.rustamsaga.numbers.numbers.presentation.NumbersFragment

// TODO what does it do???
sealed class Screen {

    abstract fun fragment(): Class<out BaseFragment<*>>

    object Details : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumberDetailsFragment::class.java
    }

    object Numbers : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumbersFragment::class.java
    }
}