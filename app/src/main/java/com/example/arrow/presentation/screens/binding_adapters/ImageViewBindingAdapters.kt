import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:layout_width")
fun setLayoutHeight(img: ImageView, dimen: Float) {
    val layoutParams = img.layoutParams
    layoutParams.height = dimen.toInt()
    img.layoutParams = layoutParams
}