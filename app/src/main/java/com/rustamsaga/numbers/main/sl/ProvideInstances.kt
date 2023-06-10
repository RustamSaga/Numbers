package com.rustamsaga.numbers.main.sl

import android.content.Context
import com.rustamsaga.numbers.numbers.data.cache.CacheModule
import com.rustamsaga.numbers.numbers.data.cloud.CloudModule
import com.rustamsaga.numbers.numbers.data.cloud.RandomApiHeader


interface ProvideInstances : ProvideRandomApiHeader {

    fun provideCloudModule(): CloudModule
    fun provideCacheModule(): CacheModule

    class Release(private val context: Context) : ProvideInstances {
        override fun provideCloudModule() = CloudModule.Base()
        override fun provideCacheModule() = CacheModule.Base(context)
        override fun provideRandomApiHeader() = RandomApiHeader.Base()
    }

    class Mock(private val context: Context) : ProvideInstances {
        override fun provideCloudModule() = CloudModule.Mock(provideRandomApiHeader())
        override fun provideCacheModule() = CacheModule.Mock(context)
        override fun provideRandomApiHeader() = RandomApiHeader.Mock()
    }
}

interface ProvideRandomApiHeader {
    fun provideRandomApiHeader(): RandomApiHeader.Combo
}