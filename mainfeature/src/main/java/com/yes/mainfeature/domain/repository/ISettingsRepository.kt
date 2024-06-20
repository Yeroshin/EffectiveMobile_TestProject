package com.yes.mainfeature.domain.repository

import com.yes.mainfeature.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    suspend fun setLastDeparture(lastDeparture: String)
    fun subscribeLastDeparture(): Flow<String>

    suspend fun setArrival(arrival: String)

}