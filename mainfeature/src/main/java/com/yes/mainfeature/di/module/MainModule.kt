package com.yes.mainfeature.di.module

import com.yes.mainfeature.data.mapper.Mapper
import com.yes.mainfeature.data.repository.OfferApi
import com.yes.mainfeature.data.repository.OfferRepository
import com.yes.mainfeature.data.repository.SettingsRepository
import com.yes.mainfeature.domain.usecase.GetDepartureUseCase
import com.yes.mainfeature.domain.usecase.GetOffersUseCase
import com.yes.mainfeature.domain.usecase.SaveDepartureUseCase
import com.yes.mainfeature.presentation.mapper.UiMapper
import com.yes.mainfeature.presentation.vm.MainViewModel
import com.yes.sharedmodule.data.dataSource.SettingsDataSource
import com.yes.sharedmodule.di.module.IoDispatcher
import dagger.Module
import dagger.Provides
import com.yes.sharedmodule.presentation.ui.BaseDependency
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
class MainModule {


    @Provides
    fun providesOfferApi(
        retrofit: Retrofit
    ): OfferApi {
        return retrofit.create(OfferApi::class.java)
    }

    @Provides
    fun providesMapper(): Mapper {
        return Mapper()
    }

    @Provides
    fun providesUiMapper(): UiMapper {
        return UiMapper()
    }

    @Provides
    fun providesOfferRepository(
        offerApi: OfferApi,
        mapper: Mapper
    ): OfferRepository {
        return OfferRepository(
            offerApi,
            mapper
        )
    }

    @Provides
    fun providesGetOffersUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        offerRepository: OfferRepository
    ): GetOffersUseCase {
        return GetOffersUseCase(
            dispatcher,
            offerRepository
        )
    }

    @Provides
    fun providesSettingsRepository(
        settingsDataSource: SettingsDataSource
    ): SettingsRepository {
        return SettingsRepository(
            settingsDataSource
        )
    }

    @Provides
    fun providesSaveDepartureUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        settingsRepository: SettingsRepository
    ): SaveDepartureUseCase {
        return SaveDepartureUseCase(
            dispatcher,
            settingsRepository
        )
    }
    @Provides
    fun providesGetDepartureUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        settingsRepository: SettingsRepository
    ): GetDepartureUseCase {
        return GetDepartureUseCase(
            dispatcher,
            settingsRepository
        )
    }


    @Provides
    fun providesMainViewModelFactory(

        getDepartureUseCase: GetDepartureUseCase,
        saveDepartureUseCase: SaveDepartureUseCase,
        getOffersUseCase: GetOffersUseCase,
        mapper: UiMapper
    ): MainViewModel.Factory {
        return MainViewModel.Factory(

            getDepartureUseCase,
            saveDepartureUseCase,
            getOffersUseCase,
            mapper
        )
    }

    @Provides
    fun providesDependency(
        factory: MainViewModel.Factory,
    ): BaseDependency {
        return BaseDependency(
            factory
        )
    }
}

