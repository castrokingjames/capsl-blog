package cc.capsl.social.feature.auth.login

import cc.capsl.social.domain.Session
import cc.capsl.social.ui.mvrx.State
import cc.capsl.social.ui.mvrx.Uninitialized
import com.airbnb.mvrx.MvRxState

data class LoginState(val session: State<Session> = Uninitialized) : MvRxState