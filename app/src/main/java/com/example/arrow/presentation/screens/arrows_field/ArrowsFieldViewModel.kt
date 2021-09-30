package com.example.arrow.presentation.screens.arrows_field

import androidx.lifecycle.ViewModel

class ArrowsFieldViewModel : ViewModel() {
    // 7 -> 49 items at all, item 5 -> item[4]
    private var _selectedItem = 1
    val selectedItem get() = _selectedItem - 1

    private fun setSelectedItem(selectedItem: Int){
        _selectedItem = selectedItem
    }
}