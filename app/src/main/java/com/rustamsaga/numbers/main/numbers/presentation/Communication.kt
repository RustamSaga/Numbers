package com.rustamsaga.numbers.main.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication<T> {

    interface Observer<T> {

        fun observe(owner: LifecycleOwner, observer: androidx.lifecycle.Observer<T>)
    }

    interface Mutate<T> : Mapper.Unit<T>

    interface Mutable<T> : Observer<T>, Mutate<T>

    abstract class Abstract<T>(
        protected val liveData: MutableLiveData<T> = MutableLiveData()
    ) : Mutable<T>{
        override fun observe(owner: LifecycleOwner, observer: androidx.lifecycle.Observer<T>) =
            liveData.observe(owner, observer)
    }

    abstract class Ui<T>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData){
        override fun map(source: T){
            liveData.value = source
        }
    }

    abstract class Post<T>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData){
        override fun map(source: T){
            liveData.postValue(source)
        }
    }
}