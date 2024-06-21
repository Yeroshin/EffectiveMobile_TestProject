package com.yes.mainfeature.data.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.yes.mainfeature.data.repository.SettingsRepository.PreferencesKeys.ARRIVAL
import com.yes.mainfeature.data.repository.SettingsRepository.PreferencesKeys.DEPARTURE
import com.yes.mainfeature.domain.repository.ISettingsRepository
import com.yes.sharedmodule.data.dataSource.SettingsDataSource
import kotlinx.coroutines.flow.Flow



class SettingsRepository(
    private val settingsDataSource: SettingsDataSource
):ISettingsRepository {
    object PreferencesKeys {
        val DEPARTURE = stringPreferencesKey("departure")
    }
    override suspend fun setLastDeparture(lastDeparture: String) {
        settingsDataSource.set(lastDeparture, DEPARTURE)
    }

    override fun subscribeLastDeparture(): Flow<String> {
        return settingsDataSource.subscribe(DEPARTURE, "Откуда - Москва")
    }



}