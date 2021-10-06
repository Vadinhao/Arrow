package com.example.arrow.presentation.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.example.arrow.presentation.screens.arrows_field.ArrowsFieldViewModel

class SelectionChangesCallBack : DiffUtil.ItemCallback<ArrowsFieldViewModel>() {
    override fun areItemsTheSame(
        oldItem: ArrowsFieldViewModel,
        newItem: ArrowsFieldViewModel
    ): Boolean {
        return oldItem.selectedItem == newItem.selectedItem
    }

    override fun areContentsTheSame(
        oldItem: ArrowsFieldViewModel,
        newItem: ArrowsFieldViewModel
    ): Boolean {
        return oldItem.arrowsFieldArray.contentEquals(newItem.arrowsFieldArray)
    }

}
