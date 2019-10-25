package cc.capsl.social.data.firebase.store

import android.net.Uri
import android.util.Log
import cc.capsl.social.data.PostData
import cc.capsl.social.data.UserData
import cc.capsl.social.data.store.PostDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.util.*

class FirebasePostDataStore : PostDataStore {

    override suspend fun create(title: String, content: String, global: Boolean, path: String?) {
        var image = ""
        if (path != null && path.isNotEmpty()) {
            image = UUID.randomUUID().toString()
            val storage = FirebaseStorage.getInstance()
            storage
                    .reference
                    .child(image)
                    .putFile(Uri.fromFile(File(path)))
                    .await()
        }

        val firestore = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        val id = UUID.randomUUID().toString()
        val post = hashMapOf(
                "id" to id,
                "title" to title,
                "content" to content,
                "public" to global,
                "image" to image,
                "date" to System.currentTimeMillis(),
                "image" to image,
                "user-id" to user!!.uid
        )
        firestore
                .collection("post")
                .document(id)
                .set(post)
    }

    override suspend fun read(): Flow<List<PostData>> {
        return flow {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val firestore = FirebaseFirestore.getInstance()
            firestore
                    .collection("post")
                    .orderBy("date", Query.Direction.DESCENDING)
                    .observe()
                    .consumeEach { snapshot ->
                        snapshot
                                .documents
                                .let { documents ->
                                    val posts = mutableListOf<PostData>()
                                    for (document in documents) {
                                        val postContent = document["content"] as String
                                        val postDate = Date(document["date"] as Long)
                                        val postID = document["id"] as String
                                        val postImage = document["image"] as String
                                        val postTitle = document["title"] as String
                                        val postPublic = document["public"] as Boolean
                                        val postUID = document["user-id"]

                                        val documents = firestore
                                                .collection("user")
                                                .whereEqualTo("id", postUID)
                                                .limit(1)
                                                .await()
                                                .documents

                                        val document = documents[0]
                                        val userID = document["id"] as String
                                        val userName = document["name"] as String
                                        val userEmail = document["email"] as String
                                        val user = UserData(userID, userName, userEmail)

                                        val post = PostData(postID, postTitle, postContent, postImage, postDate, user)
                                        if (postPublic || postUID == currentUser?.uid) {
                                            posts.add(post)
                                        }
                                    }
                                    emit(posts)
                                }
                    }

        }
    }

    override suspend fun read(id: String): Flow<List<PostData>> {
        return flow {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val firestore = FirebaseFirestore.getInstance()
            firestore
                    .collection("post")
                    .whereEqualTo("user-id", id)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .observe()
                    .consumeEach { snapshot ->
                        snapshot
                                .documents
                                .let { documents ->
                                    val posts = mutableListOf<PostData>()
                                    for (document in documents) {
                                        val postContent = document["content"] as String
                                        val postDate = Date(document["date"] as Long)
                                        val postID = document["id"] as String
                                        val postImage = document["image"] as String
                                        val postTitle = document["title"] as String
                                        val postPublic = document["public"] as Boolean
                                        val postUID = document["user-id"]

                                        val documents = firestore
                                                .collection("user")
                                                .whereEqualTo("id", postUID)
                                                .limit(1)
                                                .await()
                                                .documents

                                        val document = documents[0]
                                        val userID = document["id"] as String
                                        val userName = document["name"] as String
                                        val userEmail = document["email"] as String
                                        val user = UserData(userID, userName, userEmail)

                                        val post = PostData(postID, postTitle, postContent, postImage, postDate, user)
                                        if (postPublic || postUID == currentUser?.uid) {
                                            posts.add(post)
                                        }
                                    }
                                    emit(posts)
                                }
                    }

        }
    }
}