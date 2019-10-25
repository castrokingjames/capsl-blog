package cc.capsl.social.feature.auth.login

import android.util.Log
import cc.capsl.social.manager.SessionManager
import cc.capsl.social.ui.mvrx.Fail
import cc.capsl.social.ui.mvrx.MvRxViewModel
import cc.capsl.social.ui.mvrx.Success
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel @AssistedInject constructor(
        @Assisted state: LoginState,
        private val manager: SessionManager
) : MvRxViewModel<LoginState>(state), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun login(email: String, password: String) {
        launch(Dispatchers.IO) {
            try {
                val session = manager.login(email, password)
                withContext(Dispatchers.Main) {
                    setState { copy(session = Success(session)) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("TAG", "LoginViewModel: ${e.message}")
                    setState { copy(session = Fail(e)) }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: LoginState): LoginViewModel
    }

    companion object : MvRxViewModelFactory<LoginViewModel, LoginState> {
        override fun create(viewModelContext: ViewModelContext, state: LoginState): LoginViewModel? {
            val fragment: LoginFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }
}