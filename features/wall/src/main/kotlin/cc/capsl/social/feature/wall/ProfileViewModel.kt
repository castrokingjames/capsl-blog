package cc.capsl.social.feature.wall

import android.util.Log
import cc.capsl.social.domain.repository.PostRepository
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

class ProfileViewModel @AssistedInject constructor(
        @Assisted state: ProfileState,
        private val repository: PostRepository
) : MvRxViewModel<ProfileState>(state), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        load()
    }

    private fun load() {
        withState {
            launch(Dispatchers.IO) {
                try {
                    repository
                            .read(it.userId)
                            .collect {
                                withContext(Dispatchers.Main) {
                                    setState {
                                        copy(post = Success(it))
                                    }
                                }
                            }
                } catch (e: Exception) {
                    Log.e("TAG", "ProfileViewModel: ${e.message}")
                    setState { copy(post = Fail(e)) }
                }
            }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: ProfileState): ProfileViewModel
    }

    companion object : MvRxViewModelFactory<ProfileViewModel, ProfileState> {
        override fun create(viewModelContext: ViewModelContext, state: ProfileState): ProfileViewModel? {
            val fragment: ProfileFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }
}