package com.rustamsaga.numbers.numbers.data.cache

import com.rustamsaga.numbers.numbers.data.NumberData


class NumberDataToCache : NumberData.Mapper<NumberCache> {
    override fun map(id: String, fact: String) = NumberCache(id, fact, System.currentTimeMillis())
}