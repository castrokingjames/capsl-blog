package cc.capsl.social.feature.feeds.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import cc.capsl.social.feature.feeds.R
import cc.capsl.social.ui.common.ImageLoader
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PostRow @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val titleTextView: TextView
    private val contentTextView: TextView
    private val nameTextView: TextView
    private val thumbnailImageView: ImageView

    init {
        inflate(context, R.layout.row_post, this)
        nameTextView = findViewById(R.id.nameTextView)
        titleTextView = findViewById(R.id.titleTextView)
        contentTextView = findViewById(R.id.contentTextView)
        thumbnailImageView = findViewById(R.id.thumbnailImageView)
    }

    @TextProp
    fun setName(name: CharSequence) {
        nameTextView.text = name
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleTextView.text = title
    }

    @TextProp
    fun setContent(content: CharSequence) {
        contentTextView.text = content
    }

    @TextProp
    fun setImage(image: CharSequence) {
        val visibility = if (image == null || image.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
        thumbnailImageView.visibility = visibility

        ImageLoader.load(context, image.toString(), thumbnailImageView)
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        nameTextView.setOnClickListener(clickListener)
    }
}