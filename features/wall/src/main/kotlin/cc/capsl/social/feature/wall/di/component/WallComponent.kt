package cc.capsl.social.feature.wall.di.component

import cc.capsl.social.di.module.ViewModelFactoryModule
import cc.capsl.social.di.scope.ForActivity
import cc.capsl.social.feature.wall.WallActivity
import cc.capsl.social.feature.wall.di.module.WallModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForActivity
@Subcomponent(
        modules = [
            ViewModelFactoryModule::class,
            WallModule::class
        ]
)
interface WallComponent : AndroidInjector<WallActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<WallActivity>
}