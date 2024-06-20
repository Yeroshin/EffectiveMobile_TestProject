package com.yes.mainfeature.domain.usecase

import com.yes.mainfeature.data.repository.SettingsRepository
import com.yes.sharedmodule.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class SaveDepartureUseCase (
    dispatcher: CoroutineDispatcher,
    private val settingsRepository: SettingsRepository
) : UseCase<SaveDepartureUseCase.Params,Boolean>(dispatcher){
    override suspend fun run(params: Params?): Boolean {
        return params?.let {
            settingsRepository.setLastDeparture(it.departure)
            true
        }?:false

    }
    data class Params(val departure:String)
}