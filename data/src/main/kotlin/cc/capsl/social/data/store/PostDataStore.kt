package cc.capsl.social.data.store

import cc.capsl.social.data.PostData
import kotlinx.coroutines.flow.Flow

interface PostDataStore {

    suspend fun create(title: String, content: String, global: Boolean, image: String?)

    suspend fun read(): Flow<List<PostData>>

    suspend fun read(id: String): Flow<List<PostData>>

    interface Factory {

        fun createFirebaseDataStore(): PostDataStore
    }
}