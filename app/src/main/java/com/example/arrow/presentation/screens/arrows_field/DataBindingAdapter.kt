package com.example.arrow.presentation.screens.arrows_field

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object DataBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:layout_width")
    fun setLayoutWidth(view: ImageView, width: Float) {
        val layoutParams = view.layoutParams
        layoutParams.width = width.toInt()
        view.layoutParams = layoutParams
    }

    @JvmStatic
    @BindingAdapter("android:layout_height")
    fun setLayoutHeight(view: ImageView, height: Float) {
        val layoutParams = view.layoutParams
        layoutParams.height = height.toInt()
        view.layoutParams = layoutParams
    }
/*
    @JvmStatic
    @BindingAdapter("app:spanCount")
    fun setSpanCount(view: RecyclerView, spanCount: Int) {
        (view.layoutManager as GridLayoutManager).spanCount = spanCount
    }
*/
}