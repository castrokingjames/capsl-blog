package cc.capsl.social.feature.feeds

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


class FeedListViewModel @AssistedInject constructor(
        @Assisted state: FeedListState,
        private val repository: PostRepository
) : MvRxViewModel<FeedListState>(state), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        load()
    }

    private fun load() {
        launch(Dispatchers.IO) {
            try {
                repository
                        .read()
                        .collect {
                            withContext(Dispatchers.Main) {
                                setState {
                                    copy(post = Success(it))
                                }
                            }
                        }
            } catch (e: Exception) {
                Log.e("TAG", "FeedListViewModel: ${e.message}")
                setState { copy(post = Fail(e)) }
            }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: FeedListState): FeedListViewModel
    }

    companion object : MvRxViewModelFactory<FeedListViewModel, FeedListState> {
        override fun create(viewModelContext: ViewModelContext, state: FeedListState): FeedListViewModel? {
            val fragment: FeedListFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }
}