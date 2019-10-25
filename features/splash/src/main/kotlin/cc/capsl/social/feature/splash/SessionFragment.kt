package cc.capsl.social.feature.splash

import android.os.Bundle
import cc.capsl.social.ui.mvrx.Fail
import cc.capsl.social.ui.mvrx.Success
import cc.capsl.social.ui.navigation.Activities
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SessionFragment : BaseMvRxFragment() {

    @Inject
    lateinit var viewModelFactory: SessionViewModel.Factory

    private val viewModel: SessionViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.session) {
            is Success -> {
                onSuccess()
            }

            is Fail -> {
                onFail()
            }
        }
    }

    private fun onSuccess() {
        val intent = Activities.intentForFeeds(requireContext())
        startActivity(intent)
        requireActivity().finish()
    }

    private fun onFail() {
        val intent = Activities.intentForAuth(requireContext())
        startActivity(intent)
        requireActivity().finish()
    }
}