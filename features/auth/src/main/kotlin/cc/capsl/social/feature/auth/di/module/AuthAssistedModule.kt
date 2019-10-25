package cc.capsl.social.feature.auth.di.module

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(includes = [AssistedInject_AuthAssistedModule::class])
@AssistedModule
interface AuthAssistedModule