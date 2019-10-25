package cc.capsl.social.feature.wall.di.module

import cc.capsl.social.feature.wall.ProfileFragment
import cc.capsl.social.feature.wall.di.component.ProfileSubComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            ProfileSubComponent::class
        ]
)
abstract class WallModule {

    @Binds
    @IntoMap
    @ClassKey(ProfileFragment::class)
    abstract fun bindFeedListFragmentInjectorFactory(factory: ProfileSubComponent.Factory): AndroidInjector.Factory<*>
}