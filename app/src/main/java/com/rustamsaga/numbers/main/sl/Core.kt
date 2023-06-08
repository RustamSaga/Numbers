package com.rustamsaga.numbers.main.sl

import android.content.Context
import com.rustamsaga.numbers.random.WorkManagerWrapper
import com.rustamsaga.numbers.details.data.NumberFactDetails
import com.rustamsaga.numbers.main.presentation.NavigationCommunication
import com.rustamsaga.numbers.numbers.data.cache.CacheModule
import com.rustamsaga.numbers.numbers.data.cloud.CloudModule
import com.rustamsaga.numbers.numbers.presentation.DispatchersList
import com.rustamsaga.numbers.numbers.presentation.ManageResources

interface Core : CloudModule, CacheModule, ManageResources, ProvideNavigation,
    ProvideNumberDetails, ProvideRandomApiHeader, ProvideWorkManagerWrapper {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        private val provideInstances: ProvideInstances
    ) : Core {

        private val workManagerWrapper: WorkManagerWrapper = WorkManagerWrapper.Base(context)

        private val numberDetails = NumberFactDetails.Base()

        private val navigationCommunication = NavigationCommunication.Base()

        private val manageResources: ManageResources = ManageResources.Base(context)

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }

        private val cloudModule by lazy {
            provideInstances.provideCloudModule()
        }

        private val cacheModule by lazy {
            provideInstances.provideCacheModule()
        }

        override fun <T> service(clasz: Class<T>): T = cloudModule.service(clasz)

        override fun provideDataBase() = cacheModule.provideDataBase()

        override fun string(id: Int) = manageResources.string(id)

        override fun provideNavigation() = navigationCommunication

        override fun provideNumberDetails(): NumberFactDetails.Mutable = numberDetails

        override fun provideRandomApiHeader() = provideInstances.provideRandomApiHeader()

        override fun provideWorkManagerWrapper() = workManagerWrapper

        override fun provideDispatchers() = dispatchersList
    }
}

interface ProvideWorkManagerWrapper {
    fun provideWorkManagerWrapper(): WorkManagerWrapper
}

interface ProvideNavigation {
    fun provideNavigation(): NavigationCommunication.Mutable
}

interface ProvideNumberDetails {
    fun provideNumberDetails(): NumberFactDetails.Mutable
}