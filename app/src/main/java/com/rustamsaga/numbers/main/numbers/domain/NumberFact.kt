package com.rustamsaga.numbers.main.numbers.domain

import com.rustamsaga.numbers.main.numbers.presentation.NumberUi

data class NumberFact(
    private val id: String,
    private val fact: String
) {

    interface Mapper<T> {
        fun map(id: String, fact: String) : T
    }

    fun <T> map (mapper: Mapper<T>): T = mapper.map(id, fact)
 }

class NumberUiMapper : NumberFact.Mapper<NumberUi> {
    override fun map(id: String, fact: String): NumberUi {
        return NumberUi(id, fact)
    }

}