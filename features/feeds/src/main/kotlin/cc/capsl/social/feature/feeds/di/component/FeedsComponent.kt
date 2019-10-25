package cc.capsl.social.feature.feeds.di.component

import cc.capsl.social.di.module.ViewModelFactoryModule
import cc.capsl.social.di.scope.ForActivity
import cc.capsl.social.feature.feeds.FeedsActivity
import cc.capsl.social.feature.feeds.di.module.FeedsModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForActivity
@Subcomponent(
        modules = [
            ViewModelFactoryModule::class,
            FeedsModule::class
        ]
)
interface FeedsComponent : AndroidInjector<FeedsActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<FeedsActivity>
}