package cc.capsl.social.feature.post

import android.util.Log
import cc.capsl.social.domain.repository.PostRepository
import cc.capsl.social.ui.mvrx.MvRxViewModel
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreatePostViewModel @AssistedInject constructor(
        @Assisted state: CreatePostState,
        private val repository: PostRepository
) : MvRxViewModel<CreatePostState>(state), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun path(path: String?) {
        setState { copy(path = path) }
    }

    fun visible() {
        setState { copy(global = !global) }
    }

    fun createPost(title: String, content: String) {
        withState { state ->
            launch(Dispatchers.IO) {
                try {
                    repository.create(title, content, state.global, state.path)
                    setState { copy(posted = true) }
                } catch (e: Exception) {
                    Log.e("TAG", "CreatePostViewModel: ${e.message}")
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
        fun create(state: CreatePostState): CreatePostViewModel
    }

    companion object : MvRxViewModelFactory<CreatePostViewModel, CreatePostState> {
        override fun create(viewModelContext: ViewModelContext, state: CreatePostState): CreatePostViewModel? {
            val fragment: CreatePostFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }
}