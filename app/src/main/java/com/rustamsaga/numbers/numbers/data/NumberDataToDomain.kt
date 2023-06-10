package com.rustamsaga.numbers.numbers.data

import com.rustamsaga.numbers.numbers.domain.NumberFact

class NumberDataToDomain : NumberData.Mapper<NumberFact> {
    override fun map(id: String, fact: String) = NumberFact(id, fact)
}