package cc.capsl.social.feature.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cc.capsl.social.feature.feeds.views.postRow
import cc.capsl.social.ui.mvrx.MvRxFragment
import cc.capsl.social.ui.mvrx.Success
import cc.capsl.social.ui.mvrx.simpleController
import cc.capsl.social.ui.navigation.Activities
import com.airbnb.mvrx.fragmentViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_feed_list.*
import javax.inject.Inject

class FeedListFragment : MvRxFragment() {

    @Inject
    lateinit var viewModelFactory: FeedListViewModel.Factory

    private val viewModel: FeedListViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            val intent = Activities.intentForPost(requireContext())
            startActivity(intent)
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
                                clickListener { _ ->
                                    startActivity(Activities.intentForWall(requireContext(), it.user.id, it.user.name))
                                }
                            }
                        }
            }
        }
    }
}