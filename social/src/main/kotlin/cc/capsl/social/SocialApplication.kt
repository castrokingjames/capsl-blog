package cc.capsl.social

import android.app.Application
import cc.capsl.social.di.component.DaggerSocialComponent
import cc.capsl.social.di.module.ApplicationModule
import cc.capsl.social.glide.FirebaseImageLoader
import cc.capsl.social.ui.common.ImageLoader
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SocialApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerSocialComponent
                .factory()
                .create(ApplicationModule(this), this)
                .inject(this)

        Stetho.initializeWithDefaults(this)

        ImageLoader.install(FirebaseImageLoader())
    }
}