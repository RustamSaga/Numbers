package com.rustamsaga.numbers.main.presentation

import com.rustamsaga.numbers.numbers.presentation.Communication

// Позволяет выбрать нужную обертку LiveData
interface NavigationCommunication {

    interface Observe : Communication.Observe<NavigationStrategy>

    interface Mutate : Communication.Mutate<NavigationStrategy>

    interface Mutable : Observe, Mutate

    class Base : Communication.SingleUi<NavigationStrategy>(), Mutable
}