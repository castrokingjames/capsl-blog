package cc.capsl.social.feature.wall

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import cc.capsl.social.ui.mvrx.MvRxActivity
import cc.capsl.social.ui.navigation.Extra
import com.airbnb.mvrx.MvRx
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_wall.*
import javax.inject.Inject

class WallActivity : MvRxActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall)

        val userId = intent.extras?.getString(Extra.USER_ID) ?: ""
        val userName = intent.extras?.getString(Extra.USER_NAME) ?: ""
        val bundle = Bundle()
        bundle.putParcelable(MvRx.KEY_ARG, ProfileArgs(userId, userName))
        val navHostFragment = fragment as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.navigation_wall, bundle)
    }
}