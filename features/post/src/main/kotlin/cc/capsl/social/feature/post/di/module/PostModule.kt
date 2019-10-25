package cc.capsl.social.feature.post.di.module

import cc.capsl.social.feature.post.CreatePostFragment
import cc.capsl.social.feature.post.di.component.CreatePostSubComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            CreatePostSubComponent::class
        ]
)
abstract class PostModule {

    @Binds
    @IntoMap
    @ClassKey(CreatePostFragment::class)
    abstract fun bindCreatePostFragmentInjectorFactory(factory: CreatePostSubComponent.Factory): AndroidInjector.Factory<*>
}