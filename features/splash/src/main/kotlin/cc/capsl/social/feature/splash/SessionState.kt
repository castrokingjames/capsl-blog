package cc.capsl.social.feature.splash

import cc.capsl.social.domain.Session
import cc.capsl.social.ui.mvrx.State
import cc.capsl.social.ui.mvrx.Uninitialized
import com.airbnb.mvrx.MvRxState

data class SessionState(val session: State<Session> = Uninitialized) : MvRxState