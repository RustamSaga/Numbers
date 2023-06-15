package com.rustamsaga.numbers.numbers.presentation

import android.view.View
import com.rustamsaga.numbers.main.presentation.Handle
import com.rustamsaga.numbers.main.presentation.UiFeature
import com.rustamsaga.numbers.numbers.domain.NumbersResult
import kotlinx.coroutines.Job

abstract class NumbersFeature(
    private val communications: NumbersCommunications,
    private val numbersResultMapper: NumbersResult.Mapper<Unit>
) : UiFeature, suspend () -> NumbersResult {

    override fun handle(handle: Handle): Job {
        communications.showProgress(View.VISIBLE)
        return handle.handle(this) { result ->
            communications.showProgress(View.GONE)
            showUi(result)
        }
    }

    protected fun showUi(result: NumbersResult) = result.map(numbersResultMapper)
}