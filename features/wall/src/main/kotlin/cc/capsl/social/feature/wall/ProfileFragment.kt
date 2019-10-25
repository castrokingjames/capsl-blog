package cc.capsl.social.feature.wall

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cc.capsl.social.feature.wall.views.postRow
import cc.capsl.social.ui.mvrx.MvRxFragment
import cc.capsl.social.ui.mvrx.Success
import cc.capsl.social.ui.mvrx.simpleController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : MvRxFragment() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModel.Factory

    private val viewModel: ProfileViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withState(viewModel) {
            toolbar.title = "${it.userName}'s Profile"
        }

        toolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    override fun epoxyController() = simpleController(viewModel) { state ->
        when (val post = state.post) {
            is Success -> {
                post
                        .invoke()
                        .map {
                            postRow {
                                id(it.id)
                                name(it.user.name)
                                title(it.title)
                                image(it.image)
                                content(it.content)
                            }
                        }
            }
        }
    }
}