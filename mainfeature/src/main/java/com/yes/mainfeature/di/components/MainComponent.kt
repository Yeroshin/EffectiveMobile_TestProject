package com.yes.mainfeature.di.components

import com.yes.mainfeature.di.MainScope
import com.yes.mainfeature.di.module.MainModule
import com.yes.sharedmodule.di.component.SharedComponent
import com.yes.sharedmodule.presentation.ui.BaseDependency
import dagger.Component

@Component(
    dependencies = [SharedComponent::class],
    modules = [
        MainModule::class
    ]
)
@MainScope
interface MainComponent {
    fun getDependency(): BaseDependency
}