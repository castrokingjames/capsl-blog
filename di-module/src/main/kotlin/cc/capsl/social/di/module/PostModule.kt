package cc.capsl.social.di.module

import cc.capsl.social.data.firebase.store.FirebasePostDataStore
import cc.capsl.social.data.repository.PostDataRepository
import cc.capsl.social.data.store.PostDataStore
import cc.capsl.social.domain.repository.PostRepository
import dagger.Module
import dagger.Provides

@Module
class PostModule {

    @Provides
    fun providesPostRepository(postDataStore: PostDataStore.Factory): PostRepository {
        return PostDataRepository(postDataStore)
    }

    @Provides
    fun providesPostDataStoreFactory(postDataStore: PostDataStore): PostDataStore.Factory {
        return PostDataStoreFactory(postDataStore)
    }

    @Provides
    fun providesPostDataStore(): PostDataStore {
        return FirebasePostDataStore()
    }


    class PostDataStoreFactory(private val postDataStore: PostDataStore) : PostDataStore.Factory {

        override fun createFirebaseDataStore(): PostDataStore {
            return postDataStore
        }
    }
}