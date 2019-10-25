package cc.capsl.social.feature.wall

import cc.capsl.social.domain.Post
import cc.capsl.social.ui.mvrx.State
import cc.capsl.social.ui.mvrx.Uninitialized
import com.airbnb.mvrx.MvRxState

data class ProfileState(val userId: String, val userName: String, val post: State<List<Post>> = Uninitialized) : MvRxState {
    constructor(args: ProfileArgs) : this(userId = args.userId, userName = args.userName)
}