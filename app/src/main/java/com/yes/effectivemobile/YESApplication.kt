package com.yes.effectivemobile

import android.app.Application
import com.yes.mainfeature.di.components.DaggerMainComponent
import com.yes.mainfeature.presentation.ui.MainScreen
import com.yes.searchselectedfeature.presentation.di.components.DaggerSearchSelectedComponent
import com.yes.searchselectedfeature.presentation.ui.SearchSelectedScreen
import com.yes.sharedmodule.di.component.DaggerSharedComponent
import com.yes.sharedmodule.di.module.SharedModule
import com.yes.sharedmodule.presentation.ui.BaseDependency

class YESApplication: Application(),
    MainScreen.DependencyResolver,
SearchSelectedScreen.DependencyResolver{

    private val sharedComponent by lazy {
        DaggerSharedComponent.builder()
            .sharedModule(SharedModule(this))
            .build()
    }
    override fun resolveMainDependency(): BaseDependency {
     return DaggerMainComponent.builder()
         .sharedComponent(sharedComponent)
         .build()
         .getDependency()
    }

    override fun resolveSearchSelectedScreenDependency(): BaseDependency {
        return DaggerSearchSelectedComponent.builder()
            .sharedComponent(sharedComponent)
            .build()
            .getDependency()
    }
}