package cc.capsl.social.ui.navigation

import android.content.Context
import android.content.Intent

object Activities {

    fun intentForSplash(context: Context): Intent {
        return Intent("cc.capsl.social.feature.splash.open").setPackage(context.packageName)
    }

    fun intentForAuth(context: Context): Intent {
        return Intent("cc.capsl.social.feature.auth.open").setPackage(context.packageName)
    }

    fun intentForFeeds(context: Context): Intent {
        return Intent("cc.capsl.social.feature.feeds.open").setPackage(context.packageName)
    }

    fun intentForPost(context: Context): Intent {
        return Intent("cc.capsl.social.feature.post.open").setPackage(context.packageName)
    }

    fun intentForWall(context: Context, userId: String, userName: String): Intent {
        return Intent("cc.capsl.social.feature.wall.open")
                .setPackage(context.packageName)
                .putExtra(Extra.USER_ID, userId)
                .putExtra(Extra.USER_NAME, userName)
    }
}