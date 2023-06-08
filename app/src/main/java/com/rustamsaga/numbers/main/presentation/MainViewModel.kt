package com.rustamsaga.numbers.main.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.rustamsaga.numbers.random.WorkManagerWrapper
import com.rustamsaga.numbers.numbers.presentation.Communication

class MainViewModel(
    private val workManagerWrapper: WorkManagerWrapper,
    private val navigationCommunication: NavigationCommunication.Mutable
) : ViewModel(), Init, Communication.Observe<NavigationStrategy> {

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            navigationCommunication.map(NavigationStrategy.Replace(Screen.Numbers))
            workManagerWrapper.start()
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) =
        navigationCommunication.observe(owner, observer)
}