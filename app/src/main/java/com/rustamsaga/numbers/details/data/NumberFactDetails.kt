package com.rustamsaga.numbers.details.data

/**
 * Отвечает за сохраниение или/и чтение данных.
 * Наследуюшся от Save - будешь только сохранять данные
 * Наследуюшся от Read - будешь только чиатать данные
 * Наследуюшся от Mutable - сможешь делать то и другое.
 */
interface NumberFactDetails {
    interface Save {
        fun save(data: String)
    }

    interface Read {
        fun read(): String
    }

    interface Mutable : Save, Read

    class Base : Mutable {

        private var value = ""

        override fun save(data: String) {
            value = data
        }

        override fun read() = value
    }
}