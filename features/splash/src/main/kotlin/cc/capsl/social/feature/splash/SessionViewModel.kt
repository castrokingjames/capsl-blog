package cc.capsl.social.feature.splash

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
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

class SessionViewModel @AssistedInject constructor(
        @Assisted state: SessionState,
        private val manager: SessionManager
) : MvRxViewModel<SessionState>(state), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        load()
    }

    private fun load() {
        launch(Dispatchers.IO) {
            try {
                val session = manager.load()
                withContext(Dispatchers.Main) {
                    setState { copy(session = Success(session)) }
                }
            } catch (e: Exception) {
                Log.e("TAG", "SessionViewModel: ${e.message}")
                setState { copy(session = Fail(e)) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: SessionState): SessionViewModel
    }

    companion object : MvRxViewModelFactory<SessionViewModel, SessionState> {
        override fun create(viewModelContext: ViewModelContext, state: SessionState): SessionViewModel? {
            val fragment: SessionFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }
}