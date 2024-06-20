package com.yes.mainfeature.domain.usecase

import com.yes.mainfeature.data.repository.SettingsRepository
import com.yes.sharedmodule.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first

class GetDepartureUseCase (
    dispatcher: CoroutineDispatcher,
    private val settingsRepository: SettingsRepository
) : UseCase<Unit?, String>(dispatcher){
    override suspend fun run(params: Unit?): String {
        return settingsRepository.subscribeLastDeparture().first()
    }

}