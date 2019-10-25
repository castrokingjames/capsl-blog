package cc.capsl.social.feature.feeds.di.module

import cc.capsl.social.feature.feeds.FeedListFragment
import cc.capsl.social.feature.feeds.di.component.FeedListSubComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            FeedListSubComponent::class
        ]
)
abstract class FeedsModule {

    @Binds
    @IntoMap
    @ClassKey(FeedListFragment::class)
    abstract fun bindFeedListFragmentInjectorFactory(factory: FeedListSubComponent.Factory): AndroidInjector.Factory<*>
}