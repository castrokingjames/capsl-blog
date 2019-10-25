package cc.capsl.social.feature.auth.di.component

import cc.capsl.social.di.scope.ForFragment
import cc.capsl.social.feature.auth.di.module.AuthAssistedModule
import cc.capsl.social.feature.auth.register.RegisterFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForFragment
@Subcomponent(
        modules = [
            AuthAssistedModule::class
        ]
)
interface RegisterSubComponent : AndroidInjector<RegisterFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<RegisterFragment>
}