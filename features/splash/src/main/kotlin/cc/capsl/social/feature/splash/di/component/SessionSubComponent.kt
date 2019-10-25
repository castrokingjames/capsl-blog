package cc.capsl.social.feature.splash.di.component

import cc.capsl.social.di.scope.ForFragment
import cc.capsl.social.feature.splash.SessionFragment
import cc.capsl.social.feature.splash.di.module.SplashAssistedModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForFragment
@Subcomponent(
        modules = [
            SplashAssistedModule::class
        ]
)
interface SessionSubComponent : AndroidInjector<SessionFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<SessionFragment>
}