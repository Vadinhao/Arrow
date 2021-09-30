package com.example.arrow.presentation.screens.arrows_field

import com.example.arrow.R

sealed interface ArrowsSelection{
    object SELECTED: ArrowsSelection {
        const val border = R.drawable.image_selected_item_border
    }
    object UNSELECTED: ArrowsSelection{
        const val border = R.drawable.image_item_border
    }
}