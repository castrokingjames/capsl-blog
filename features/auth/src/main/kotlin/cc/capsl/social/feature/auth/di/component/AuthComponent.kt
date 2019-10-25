package cc.capsl.social.feature.auth.di.component

import cc.capsl.social.di.module.ViewModelFactoryModule
import cc.capsl.social.di.scope.ForActivity
import cc.capsl.social.feature.auth.AuthActivity
import cc.capsl.social.feature.auth.di.module.AuthModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForActivity
@Subcomponent(
        modules = [
            ViewModelFactoryModule::class,
            AuthModule::class
        ]
)
interface AuthComponent : AndroidInjector<AuthActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<AuthActivity>
}