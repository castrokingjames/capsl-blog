package cc.capsl.social.feature.splash.di.component

import cc.capsl.social.di.module.ViewModelFactoryModule
import cc.capsl.social.di.scope.ForActivity
import cc.capsl.social.feature.splash.SplashActivity
import cc.capsl.social.feature.splash.di.module.SplashModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForActivity
@Subcomponent(
        modules = [
            ViewModelFactoryModule::class,
            SplashModule::class
        ]
)
interface SplashComponent : AndroidInjector<SplashActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<SplashActivity>
}