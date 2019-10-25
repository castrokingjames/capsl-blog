package cc.capsl.social.feature.post.di.component

import cc.capsl.social.di.scope.ForFragment
import cc.capsl.social.feature.post.di.module.PostAssistedModule
import cc.capsl.social.feature.post.CreatePostFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ForFragment
@Subcomponent(
        modules = [
            PostAssistedModule::class
        ]
)
interface CreatePostSubComponent : AndroidInjector<CreatePostFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<CreatePostFragment>
}