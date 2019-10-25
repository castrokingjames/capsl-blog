package cc.capsl.social.data.repository

import cc.capsl.social.data.store.PostDataStore
import cc.capsl.social.domain.Post
import cc.capsl.social.domain.User
import cc.capsl.social.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PostDataRepository(private val factory: PostDataStore.Factory) : PostRepository {

    override suspend fun create(title: String, content: String, global: Boolean, image: String?) {
        var store = factory.createFirebaseDataStore()
        store.create(title, content, global, image)
    }

    override suspend fun read(): Flow<List<Post>> {
        var store = factory.createFirebaseDataStore()
        return flow {
            store
                    .read()
                    .collect { data ->
                        data
                                .map {
                                    var user = User(it.user.id, it.user.name, it.user.email)
                                    Post(it.id, it.title, it.content, it.image, it.date, user)
                                }
                                .apply {
                                    emit(this)
                                }
                    }
        }
    }

    override suspend fun read(id: String): Flow<List<Post>> {
        var store = factory.createFirebaseDataStore()
        return flow {
            store
                    .read(id)
                    .collect { data ->
                        data
                                .map {
                                    var user = User(it.user.id, it.user.name, it.user.email)
                                    Post(it.id, it.title, it.content, it.image, it.date, user)
                                }
                                .apply {
                                    emit(this)
                                }
                    }
        }
    }
}