package cc.capsl.social.di.module

import cc.capsl.social.manager.SessionManager
import cc.capsl.social.manager.firebase.FirebaseSessionManager
import dagger.Module
import dagger.Provides

@Module
class SessionModule {

    @Provides
    fun providesSessionManager(): SessionManager {
        return FirebaseSessionManager()
    }
}