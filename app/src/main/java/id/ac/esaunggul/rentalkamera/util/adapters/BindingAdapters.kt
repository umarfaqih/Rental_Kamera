package id.ac.esaunggul.rentalkamera.util.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun setError(viewGroup: TextInputLayout, error: LiveData<Int?>) {
    if (error.value == null) {
        viewGroup.error = null
    } else {
        error.value?.let { resId ->
            viewGroup.error = viewGroup.context
                .getString((resId))
        }
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}