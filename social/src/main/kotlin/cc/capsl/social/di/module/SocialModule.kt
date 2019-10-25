package cc.capsl.social.di.module

import cc.capsl.social.feature.auth.AuthActivity
import cc.capsl.social.feature.auth.di.component.AuthComponent
import cc.capsl.social.feature.feeds.FeedsActivity
import cc.capsl.social.feature.feeds.di.component.FeedsComponent
import cc.capsl.social.feature.post.PostActivity
import cc.capsl.social.feature.post.di.component.PostComponent
import cc.capsl.social.feature.splash.SplashActivity
import cc.capsl.social.feature.splash.di.component.SplashComponent
import cc.capsl.social.feature.wall.WallActivity
import cc.capsl.social.feature.wall.di.component.WallComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            SplashComponent::class,
            AuthComponent::class,
            PostComponent::class,
            FeedsComponent::class,
            WallComponent::class
        ]
)
abstract class SocialModule {

    @Binds
    @IntoMap
    @ClassKey(SplashActivity::class)
    abstract fun bindSplashActivityInjectorFactory(factory: SplashComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(AuthActivity::class)
    abstract fun bindAuthActivityInjectorFactory(factory: AuthComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(PostActivity::class)
    abstract fun bindPostActivityInjectorFactory(factory: PostComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(FeedsActivity::class)
    abstract fun bindFeedsActivityInjectorFactory(factory: FeedsComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(WallActivity::class)
    abstract fun bindWallActivityInjectorFactory(factory: WallComponent.Factory): AndroidInjector.Factory<*>

}