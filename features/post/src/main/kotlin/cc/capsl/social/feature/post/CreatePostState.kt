package cc.capsl.social.feature.post

import com.airbnb.mvrx.MvRxState

data class CreatePostState(val global: Boolean = true, val path: String? = "", val posted: Boolean = false) : MvRxState