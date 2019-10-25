package cc.capsl.social.feature.auth.register

import cc.capsl.social.domain.Session
import cc.capsl.social.ui.mvrx.State
import cc.capsl.social.ui.mvrx.Uninitialized
import com.airbnb.mvrx.MvRxState

data class RegisterState(val session: State<Session> = Uninitialized) : MvRxState