package cc.capsl.social.feature.post

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.afollestad.materialdialogs.MaterialDialog
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_create_post.*
import javax.inject.Inject

class CreatePostFragment : BaseMvRxFragment() {

    private val PICK_IMAGE_REQUEST = 10
    private val PERMISSION_REQUEST = 11

    @Inject
    lateinit var viewModelFactory: CreatePostViewModel.Factory

    private val viewModel: CreatePostViewModel by fragmentViewModel()
    private lateinit var dialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadImageView.setOnClickListener {
            pickImage()
        }

        sendImageView.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            if (title.isNullOrEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.post_is_empty), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.createPost(title, content)

            dialog = MaterialDialog(requireContext())
                    .title(R.string.creating_post)
                    .cancelable(false)
                    .message(R.string.loading_message)

            dialog.show()
        }

        imageView.setOnClickListener {
            imageView.setImageBitmap(null)
            viewModel.path(null)
        }

        visibilityImageView.setOnClickListener {
            viewModel.visible()
        }

        toolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        val resource = if (state.global)
            R.drawable.ic_public_white_24dp
        else
            R.drawable.ic_lock_outline_white_24dp
        visibilityImageView.setImageResource(resource)
        val title = if (state.global) {
            "Create Public Post"
        } else {
            "Create Private Post"
        }
        toolbar.title = title

        if (state.posted) {
            dialog.dismiss()
            requireActivity().finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    data.data?.let {
                        val activity = requireActivity()
                        val bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, it)
                        imageView.setImageBitmap(bitmap)

                        val path = path(it)
                        viewModel.path(path)
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage()
        }
    }

    private fun pickImage() {
        if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST)
        }
    }

    private fun path(uri: Uri): String? {
        val context = requireContext()
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        val index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: 0
        cursor?.moveToFirst()
        val path = cursor?.getString(index)
        cursor?.close()
        return path
    }
}