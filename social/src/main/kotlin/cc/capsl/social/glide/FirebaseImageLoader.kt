package cc.capsl.social.glide

import android.content.Context
import android.util.Log
import android.widget.ImageView
import cc.capsl.social.ui.common.ImageLoader
import com.google.firebase.storage.FirebaseStorage

class FirebaseImageLoader : ImageLoader.Loader {

    override fun load(context: Context, url: String, view: ImageView) {
        if (url == null || url.isEmpty()) {
            Log.w("FirebaseImageLoader", "Invalid URL")
            return
        }
        val reference = FirebaseStorage.getInstance().reference.child(url)
        GlideApp.with(context).load(reference).into(view)
    }
}

