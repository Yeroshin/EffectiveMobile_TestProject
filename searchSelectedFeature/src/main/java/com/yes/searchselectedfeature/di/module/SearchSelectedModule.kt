package com.yes.searchselectedfeature.presentation.di.module

import com.yes.searchselectedfeature.data.mapper.Mapper
import com.yes.searchselectedfeature.data.repository.FlightApi
import com.yes.searchselectedfeature.data.repository.FlightsRepository
import com.yes.searchselectedfeature.domain.repository.IFlightsRepository
import com.yes.searchselectedfeature.domain.usecase.GetFlightsUseCase
import com.yes.searchselectedfeature.presentation.mapper.MapperUi
import com.yes.searchselectedfeature.presentation.vm.SearchSelectedViewModel
import com.yes.sharedmodule.di.module.IoDispatcher
import com.yes.sharedmodule.presentation.ui.BaseDependency
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
class SearchSelectedModule {
    @Provides
    fun providesFlightApi(
        retrofit: Retrofit
    ): FlightApi{
        return retrofit.create(FlightApi::class.java)

    }
    @Provides
    fun providesMapper(): Mapper{
        return Mapper()
    }
    @Provides
    fun providesFlightsRepository(
        offerApi: FlightApi,
        mapper: Mapper
    ): IFlightsRepository {
        return FlightsRepository(
            offerApi,
            mapper
        )
    }
    @Provides
    fun providesGetFlightsUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        flightsRepository: IFlightsRepository
    ): GetFlightsUseCase {
        return GetFlightsUseCase(
            dispatcher,
            flightsRepository
        )
    }
    @Provides
    fun providesMapperUi(): MapperUi {
        return MapperUi()
    }
    @Provides
    fun providesMainViewModelFactory(
        mapper: MapperUi,
        getFlightsUseCase: GetFlightsUseCase
    ): SearchSelectedViewModel.Factory {
        return SearchSelectedViewModel.Factory(
            mapper,
            getFlightsUseCase
        )
    }

    @Provides
    fun providesDependency(
        factory: SearchSelectedViewModel.Factory,
    ): BaseDependency {
        return BaseDependency(
            factory
        )
    }
}