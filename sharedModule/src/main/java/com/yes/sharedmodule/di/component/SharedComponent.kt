package com.yes.sharedmodule.di.component

import com.yes.sharedmodule.data.dataSource.SettingsDataSource
import com.yes.sharedmodule.di.module.IoDispatcher
import com.yes.sharedmodule.di.module.MainDispatcher
import com.yes.sharedmodule.di.module.SharedModule
import com.yes.sharedmodule.presentation.ui.BaseDependency
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SharedModule::class,
    ]
)

interface SharedComponent {
    @IoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher
    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher
    fun providesSettingsDataSource(): SettingsDataSource
    fun providesRetrofit(): Retrofit
}