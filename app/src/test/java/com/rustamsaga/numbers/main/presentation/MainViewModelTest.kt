package com.rustamsaga.numbers.main.presentation

import com.rustamsaga.numbers.numbers.presentation.BaseTest
import com.rustamsaga.numbers.random.WorkManagerWrapper
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest : BaseTest() {

    @Test
    fun `test navigation at start`() {
        val navigation = TestNavigationCommunication()
        val workManagerWrapper = TestWorkManagerWrapper()
        val mainViewModel = MainViewModel(workManagerWrapper, navigation)

        mainViewModel.init(true)
        assertEquals(1, navigation.count)
        assertEquals(NavigationStrategy.Replace(Screen.Numbers), navigation.strategy)
        assertEquals(1, workManagerWrapper.startCalledCount)

        mainViewModel.init(false)
        assertEquals(1, navigation.count)
        assertEquals(1, workManagerWrapper.startCalledCount)
    }

    private class TestWorkManagerWrapper : WorkManagerWrapper {
        var startCalledCount = 0

        override fun start() {
            startCalledCount++
        }
    }
}