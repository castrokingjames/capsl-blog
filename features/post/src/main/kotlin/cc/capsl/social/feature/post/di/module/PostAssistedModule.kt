package cc.capsl.social.feature.post.di.module

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(includes = [AssistedInject_PostAssistedModule::class])
@AssistedModule
interface PostAssistedModule