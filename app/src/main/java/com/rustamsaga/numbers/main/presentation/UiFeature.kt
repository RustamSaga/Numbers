package com.rustamsaga.numbers.main.presentation

import kotlinx.coroutines.Job

// TODO what does it do???
interface UiFeature {

    fun handle(handle: Handle): Job
}