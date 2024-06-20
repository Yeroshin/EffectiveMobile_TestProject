package com.yes.ticketsfeature.di.components

import com.yes.sharedmodule.di.component.SharedComponent
import com.yes.sharedmodule.presentation.ui.BaseDependency
import com.yes.ticketsfeature.di.TicketsScope
import com.yes.ticketsfeature.di.module.TicketsModule
import dagger.Component


@Component(
    dependencies = [SharedComponent::class],
    modules = [
        TicketsModule::class
    ]
)
@TicketsScope
interface TicketsComponent {
    fun getDependency(): BaseDependency
}