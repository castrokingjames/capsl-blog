package cc.capsl.social.di.module

import androidx.lifecycle.ViewModelProvider
import cc.capsl.social.di.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}