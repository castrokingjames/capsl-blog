package cc.capsl.social.feature.feeds

import cc.capsl.social.domain.Post
import cc.capsl.social.ui.mvrx.State
import cc.capsl.social.ui.mvrx.Uninitialized
import com.airbnb.mvrx.MvRxState

data class FeedListState(val post: State<List<Post>> = Uninitialized) : MvRxState