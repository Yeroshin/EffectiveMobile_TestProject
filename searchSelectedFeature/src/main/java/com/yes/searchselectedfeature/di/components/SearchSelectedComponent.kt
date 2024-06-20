package com.yes.searchselectedfeature.presentation.di.components

import com.yes.searchselectedfeature.presentation.di.SearchSelectedScope
import com.yes.searchselectedfeature.presentation.di.module.SearchSelectedModule
import com.yes.sharedmodule.di.component.SharedComponent
import com.yes.sharedmodule.presentation.ui.BaseDependency
import dagger.Component


@Component(
    dependencies = [SharedComponent::class],
    modules = [
        SearchSelectedModule::class
    ]
)
@SearchSelectedScope
interface SearchSelectedComponent {
    fun getDependency(): BaseDependency
}