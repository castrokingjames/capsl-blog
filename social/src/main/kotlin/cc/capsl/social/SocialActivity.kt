package cc.capsl.social

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.capsl.social.glide.GlideApp
import cc.capsl.social.ui.navigation.Activities
import com.bumptech.glide.Glide

class SocialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Activities.intentForSplash(this))
        finish()
    }
}