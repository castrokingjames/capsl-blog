package cc.capsl.social.ui.common

import android.content.Context
import android.widget.ImageView

object ImageLoader {

    private var loader: Loader = DefaultLoader

    fun install(loader: Loader) {
        this.loader = loader
    }

    fun load(context: Context, url: String, view: ImageView) {
        loader.load(context, url, view)
    }

    interface Loader {
        fun load(context: Context, url: String, view: ImageView)
    }

    object DefaultLoader : Loader {
        override fun load(context: Context, url: String, view: ImageView) {

        }
    }
}