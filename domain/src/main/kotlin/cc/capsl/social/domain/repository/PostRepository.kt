package cc.capsl.social.domain.repository

import cc.capsl.social.domain.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun create(title: String, content: String, global: Boolean, image: String?)

    suspend fun read(): Flow<List<Post>>

    suspend fun read(id: String): Flow<List<Post>>

}