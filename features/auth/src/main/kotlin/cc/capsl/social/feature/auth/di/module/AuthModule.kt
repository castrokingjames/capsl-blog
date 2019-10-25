package cc.capsl.social.feature.auth.di.module

import cc.capsl.social.feature.auth.login.LoginFragment
import cc.capsl.social.feature.auth.register.RegisterFragment
import cc.capsl.social.feature.auth.di.component.LoginSubComponent
import cc.capsl.social.feature.auth.di.component.RegisterSubComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            RegisterSubComponent::class,
            LoginSubComponent::class
        ]
)
abstract class AuthModule {

    @Binds
    @IntoMap
    @ClassKey(RegisterFragment::class)
    abstract fun bindRegisterFragmentInjectorFactory(factory: RegisterSubComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(LoginFragment::class)
    abstract fun bindLoginFragmentInjectorFactory(factory: LoginSubComponent.Factory): AndroidInjector.Factory<*>
}