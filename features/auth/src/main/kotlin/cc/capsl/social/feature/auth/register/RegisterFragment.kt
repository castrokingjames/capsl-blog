package cc.capsl.social.feature.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cc.capsl.social.domain.Session
import cc.capsl.social.feature.auth.R
import cc.capsl.social.ui.mvrx.Fail
import cc.capsl.social.ui.mvrx.Success
import cc.capsl.social.ui.navigation.Activities
import com.afollestad.materialdialogs.MaterialDialog
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

class RegisterFragment : BaseMvRxFragment() {

    @Inject
    lateinit var viewModelFactory: RegisterViewModel.Factory

    private lateinit var dialog: MaterialDialog

    private val viewModel: RegisterViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerCardView.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.register(name, email, password)

            dialog = MaterialDialog(requireContext())
                    .title(R.string.creating_user)
                    .cancelable(false)
                    .message(R.string.loading_message)

            dialog.show()
        }
        loginTextView.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        when (val session = state.session) {
            is Success -> {
                onSuccess()
            }

            is Fail -> {
                onFail(session)
            }
        }
    }

    private fun onSuccess() {
        dialog.dismiss()
        val intent = Activities.intentForFeeds(requireContext())
        startActivity(intent)
        requireActivity().finish()
    }

    private fun onFail(state: Fail<Session>) {
        dialog.dismiss()
        Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
    }
}