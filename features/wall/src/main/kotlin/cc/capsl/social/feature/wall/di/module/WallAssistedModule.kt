package cc.capsl.social.feature.wall.di.module

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(includes = [AssistedInject_WallAssistedModule::class])
@AssistedModule
interface WallAssistedModule