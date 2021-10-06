package com.example.arrow.presentation.screens.arrows_field

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arrow.constants.Constants
import com.example.arrow.domain.models.arrow.Arrows
import com.example.arrow.domain.models.position.Position

class ArrowsFieldViewModel : ViewModel() {
    //7 rows -> 49 items at all, item num 5 -> it's item[4]
    private var _selectedItem = MutableLiveData<Position>()
    val selectedItem: LiveData<Position> get() = _selectedItem

    private var _arrowsFieldArray: Array<Array<Int>> = Arrows(Constants.SPANCOUNT).arrows
    val arrowsFieldArray get() = _arrowsFieldArray

    fun setSelectedItem(selectedItem: Position){
        _selectedItem.value = selectedItem
    }



}