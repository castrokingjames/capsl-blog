package cc.capsl.social.feature.wall.di.component

import cc.capsl.social.di.scope.ForFragment
import cc.capsl.social.feature.wall.di.module.WallAssistedModule
import cc.capsl.social.feature.wall.ProfileFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForFragment
@Subcomponent(
        modules = [
            WallAssistedModule::class
        ]
)
interface ProfileSubComponent : AndroidInjector<ProfileFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<ProfileFragment>
}