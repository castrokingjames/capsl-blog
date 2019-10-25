package cc.capsl.social.feature.auth.register

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

class RegisterViewModel @AssistedInject constructor(
        @Assisted state: RegisterState,
        private val manager: SessionManager
) : MvRxViewModel<RegisterState>(state), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun register(name: String, email: String, password: String) {
        launch(Dispatchers.IO) {
            try {
                val session = manager.register(name, email, password)
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
        fun create(state: RegisterState): RegisterViewModel
    }

    companion object : MvRxViewModelFactory<RegisterViewModel, RegisterState> {
        override fun create(viewModelContext: ViewModelContext, state: RegisterState): RegisterViewModel? {
            val fragment: RegisterFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }
}