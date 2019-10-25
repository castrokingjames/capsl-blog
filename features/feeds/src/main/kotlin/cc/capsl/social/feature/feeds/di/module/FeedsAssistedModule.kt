package cc.capsl.social.feature.feeds.di.module

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(includes = [AssistedInject_FeedsAssistedModule::class])
@AssistedModule
interface FeedsAssistedModule