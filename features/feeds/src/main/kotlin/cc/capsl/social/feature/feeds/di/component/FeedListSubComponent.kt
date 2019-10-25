package cc.capsl.social.feature.feeds.di.component

import cc.capsl.social.di.scope.ForFragment
import cc.capsl.social.feature.feeds.FeedListFragment
import cc.capsl.social.feature.feeds.di.module.FeedsAssistedModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForFragment
@Subcomponent(
        modules = [
            FeedsAssistedModule::class
        ]
)
interface FeedListSubComponent : AndroidInjector<FeedListFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<FeedListFragment>
}