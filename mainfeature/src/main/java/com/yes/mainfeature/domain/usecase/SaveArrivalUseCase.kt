package com.yes.mainfeature.domain.usecase

import com.yes.mainfeature.data.repository.SettingsRepository
import com.yes.sharedmodule.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class SaveArrivalUseCase (
    dispatcher: CoroutineDispatcher,
    private val settingsRepository: SettingsRepository
) : UseCase<SaveArrivalUseCase.Params, Boolean>(dispatcher){
    override suspend fun run(params: Params?): Boolean {
        return params?.let {
            settingsRepository.setArrival(it.arrival)
            true
        }?:false

    }
    data class Params(val arrival:String)
}