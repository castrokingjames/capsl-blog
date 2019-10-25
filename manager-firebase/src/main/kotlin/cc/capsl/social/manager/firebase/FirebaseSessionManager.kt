package cc.capsl.social.manager.firebase

import cc.capsl.social.domain.Session
import cc.capsl.social.manager.SessionManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseSessionManager : SessionManager {

    override suspend fun login(email: String, password: String): Session {
        try {
            val task = FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .await()
            val user = task.user
            val uid = user?.uid ?: ""
            return Session(uid)
        } catch (exception: Exception) {
            throw exception
        }
    }

    override suspend fun load(): Session {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            throw(Exception("No Existing Session"))
        } else {
            return Session(user.uid)
        }
    }

    override suspend fun register(name: String, email: String, password: String): Session {
        try {
            val task = FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .await()
            val user = task.user
            val uid = user?.uid ?: ""

            val firestore = FirebaseFirestore.getInstance()
            val map = hashMapOf(
                    "id" to uid,
                    "name" to name,
                    "email" to email
            )

            firestore
                    .collection("user")
                    .document(uid)
                    .set(map)

            return Session(uid)
        } catch (exception: Exception) {
            throw exception
        }
    }
}