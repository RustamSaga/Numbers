package com.rustamsaga.numbers.main

import java.time.OffsetDateTime

fun main(){
    val now = OffsetDateTime.now()
    println(now)
    println("day of year - " + now.dayOfYear)
    println("day of month - " + now.dayOfMonth)
    println("day of week - " + now.dayOfWeek)
    println("year - " + now.year)
    println("month - " + now.month)
    println("monthValue - " + now.monthValue)
    println("hour - " + now.hour)
    println("minute - " + now.minute)
    println("second - " + now.second)
    println("nano - " + now.nano)
    println("offset - " + now.offset)

}