package cc.capsl.social.feature.post.di.component

import cc.capsl.social.di.module.ViewModelFactoryModule
import cc.capsl.social.di.scope.ForActivity
import cc.capsl.social.feature.post.di.module.PostModule
import cc.capsl.social.feature.post.PostActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForActivity
@Subcomponent(
        modules = [
            ViewModelFactoryModule::class,
            PostModule::class
        ]
)
interface PostComponent : AndroidInjector<PostActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PostActivity>
}