package cc.capsl.social.feature.auth.login

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
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseMvRxFragment() {

    @Inject
    lateinit var viewModelFactory: LoginViewModel.Factory

    private lateinit var dialog: MaterialDialog

    private val viewModel: LoginViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginCardView.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.login(email, password)

            dialog = MaterialDialog(requireContext())
                    .title(R.string.authenticating)
                    .cancelable(false)
                    .message(R.string.loading_message)

            dialog.show()
        }

        registerTextView.setOnClickListener {
            findNavController().navigate(R.id.register)
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