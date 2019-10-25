package cc.capsl.social.feature.splash.di.module

import cc.capsl.social.feature.splash.di.component.SessionSubComponent
import cc.capsl.social.feature.splash.SessionFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            SessionSubComponent::class
        ]
)
abstract class SplashModule {

    @Binds
    @IntoMap
    @ClassKey(SessionFragment::class)
    abstract fun bindSessionFragmentInjectorFactory(factory: SessionSubComponent.Factory): AndroidInjector.Factory<*>

}