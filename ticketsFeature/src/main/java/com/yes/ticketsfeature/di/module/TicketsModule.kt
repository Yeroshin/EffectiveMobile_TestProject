package com.yes.ticketsfeature.di.module

import com.yes.sharedmodule.di.module.IoDispatcher
import com.yes.sharedmodule.presentation.ui.BaseDependency
import com.yes.ticketsfeature.data.mapper.Mapper
import com.yes.ticketsfeature.data.repository.TicketsApi
import com.yes.ticketsfeature.data.repository.TicketsRepository
import com.yes.ticketsfeature.domain.usecase.GetTicketsUseCase
import com.yes.ticketsfeature.presentation.mapper.MapperUi
import com.yes.ticketsfeature.presentation.vm.TicketsViewModel
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
class TicketsModule {
    @Provides
    fun providesTicketsApi(
        retrofit: Retrofit
    ): TicketsApi {
        return retrofit.create(TicketsApi::class.java)
    }

    @Provides
    fun providesMapper(): Mapper {
        return Mapper()
    }

    @Provides
    fun providesTicketsRepository(
        ticketsApi: TicketsApi,
        mapper: Mapper
    ): TicketsRepository {
        return TicketsRepository(
            ticketsApi,
            mapper
        )
    }

    @Provides
    fun providesUiMapper(): MapperUi {
        return MapperUi()
    }

    @Provides
    fun providesTicketsUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        ticketsRepository: TicketsRepository
    ): GetTicketsUseCase {
        return GetTicketsUseCase(
            dispatcher,
            ticketsRepository
        )
    }

    @Provides
    fun providesTicketsViewModelFactory(
        mapperUi: MapperUi,
        getTicketsUseCase: GetTicketsUseCase
    ): TicketsViewModel.Factory {
        return TicketsViewModel.Factory(
            mapperUi,
            getTicketsUseCase
        )
    }

    @Provides
    fun providesDependency(
        factory: TicketsViewModel.Factory,
    ): BaseDependency {
        return BaseDependency(
            factory
        )
    }
}