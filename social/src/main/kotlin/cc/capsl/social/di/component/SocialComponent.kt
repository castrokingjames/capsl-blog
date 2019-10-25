package cc.capsl.social.di.component

import android.app.Application
import cc.capsl.social.SocialApplication
import cc.capsl.social.di.module.*
import cc.capsl.social.di.scope.ForApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@ForApplication
@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            SessionModule::class,
            SocialModule::class,
            PostModule::class
        ]
)
interface SocialComponent : AndroidInjector<SocialApplication> {

    @Component.Factory
    interface Factory {

        fun create(module: ApplicationModule, @BindsInstance application: Application): SocialComponent
    }

    override fun inject(app: SocialApplication)
}