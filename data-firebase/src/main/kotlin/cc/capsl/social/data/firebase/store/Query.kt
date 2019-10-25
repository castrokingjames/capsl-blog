package cc.capsl.social.data.firebase.store

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.IllegalStateException
import kotlin.coroutines.resumeWithException

fun Query.observe(): ReceiveChannel<QuerySnapshot> {
    val channel = Channel<QuerySnapshot>()
    addSnapshotListener { snapshot, exception ->
        exception?.let {
            channel.close(it)
            return@addSnapshotListener
        }

        if (snapshot == null) {
            channel.close()
            return@addSnapshotListener
        }

        channel.sendBlocking(snapshot)
    }
    return channel
}

suspend fun Query.await(): QuerySnapshot {
    return suspendCancellableCoroutine { continuation ->
        get().addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                continuation.resume(it.result!!) {}
            } else {
                continuation.resumeWithException(it.exception ?: IllegalStateException())
            }
        }
    }
}