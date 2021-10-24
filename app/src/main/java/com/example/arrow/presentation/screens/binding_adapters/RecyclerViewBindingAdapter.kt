package com.example.arrow.presentation.screens.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("android:layout_width")
fun setLayoutWidth(img: ImageView, dimen: Float) {
    val layoutParams = img.layoutParams
    layoutParams.width = dimen.toInt()
    img.layoutParams = layoutParams
}

@BindingAdapter("app:spanCount")
fun setSpanCount(rv: RecyclerView, num: Float){
    val layoutManager = GridLayoutManager(rv.context, num.toInt())
    layoutManager.spanCount = num.toInt()
    rv.layoutManager = layoutManager
}
