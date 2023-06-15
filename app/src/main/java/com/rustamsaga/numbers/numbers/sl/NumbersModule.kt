package com.rustamsaga.numbers.numbers.sl

import com.rustamsaga.numbers.main.sl.Core
import com.rustamsaga.numbers.main.sl.Module
import com.rustamsaga.numbers.numbers.data.BaseNumbersRepository
import com.rustamsaga.numbers.numbers.data.HandleDataRequest
import com.rustamsaga.numbers.numbers.data.HandleDomainError
import com.rustamsaga.numbers.numbers.data.NumberDataToDomain
import com.rustamsaga.numbers.numbers.data.cache.NumberDataToCache
import com.rustamsaga.numbers.numbers.data.cache.NumbersCacheDataSource
import com.rustamsaga.numbers.numbers.data.cloud.NumbersCloudDataSource
import com.rustamsaga.numbers.numbers.data.cloud.NumbersService
import com.rustamsaga.numbers.numbers.domain.*
import com.rustamsaga.numbers.numbers.presentation.*


class NumbersModule(
    private val core: Core,
    private val provideRepository: ProvideNumbersRepository
) : Module<NumbersViewModel.Base> {

    override fun viewModel(): NumbersViewModel.Base {
        val repository = provideRepository.provideNumbersRepository()
        val communications = NumbersCommunications.Base(
            ProgressCommunication.Base(),
            NumbersStateCommunication.Base(),
            NumbersListCommunication.Base()
        )
        val mapper = NumbersResultMapper(communications, NumberUiMapper())
        val interactor = NumbersInteractor.Base(
            repository,
            HandleRequest.Base(
                HandleError.Base(core),
                repository
            ),
            core.provideNumberDetails()
        )
        return NumbersViewModel.Base(
            core.provideDispatchers(),
            NumbersInitialFeature(communications, mapper, interactor),
            NumbersFactFeature.Base(core, communications, mapper, interactor),
            RandomNumberFactFeature(interactor, communications, mapper),
            ShowDetails.Base(interactor, core.provideNavigation(), DetailsUi()),
            communications
        )
    }
}

interface ProvideNumbersRepository {

    fun provideNumbersRepository(): NumbersRepository

    class Base(private val core: Core) : ProvideNumbersRepository {

        override fun provideNumbersRepository(): NumbersRepository {
            val cacheDataSource = NumbersCacheDataSource.Base(
                core.provideDataBase().numbersDao(),
                NumberDataToCache()
            )
            return BaseNumbersRepository(
                NumbersCloudDataSource.Base(
                    core.service(NumbersService::class.java),
                    core.provideRandomApiHeader()
                ),
                cacheDataSource,
                HandleDataRequest.Base(
                    cacheDataSource,
                    NumberDataToDomain(),
                    HandleDomainError()
                ),
                NumberDataToDomain()
            )
        }
    }
}