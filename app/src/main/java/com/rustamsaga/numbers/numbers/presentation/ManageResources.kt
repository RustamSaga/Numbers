package com.rustamsaga.numbers.numbers.presentation

import android.content.Context
import androidx.annotation.StringRes

/**
 * todo
 * Interface который позволяет получить строку из ресурсов там где нет context
 */
interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context) : ManageResources {
        override fun string(id: Int): String = context.getString(id)
    }
}