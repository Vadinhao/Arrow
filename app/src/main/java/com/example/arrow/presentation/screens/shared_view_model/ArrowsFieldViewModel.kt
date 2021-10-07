package com.example.arrow.presentation.screens.shared_view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arrow.constants.Constants
import com.example.arrow.domain.models.arrow.Arrow
import com.example.arrow.domain.models.arrow.Arrows
import com.example.arrow.domain.models.arrow.Positions
import com.example.arrow.domain.models.position.Position

class ArrowsFieldViewModel : ViewModel() {
    //7 rows -> 49 items at all, item num 5 -> it's item[4]
    private var _selectedItem = MutableLiveData<Position>()
    val selectedItem: LiveData<Position> get() = _selectedItem

    private var _arrowsFieldArray = MutableLiveData<Array<Array<Int>>>()
    val arrowsFieldArray: LiveData<Array<Array<Int>>> get() = _arrowsFieldArray

    fun setSelectedItem(selectedItem: Position){
        _selectedItem.value = selectedItem
    }

    fun setArrowsFieldArray(){
        _arrowsFieldArray.value = Arrows(Constants.SPANCOUNT).arrows
    }

    fun changeOrientationOfSelectedItem(){
        val arr = _arrowsFieldArray.value!!

        if(arr[selectedItem.value!!.rows()][selectedItem.value!!.column()] == Positions.Last)
            arr[selectedItem.value!!.rows()][selectedItem.value!!.column()] = Positions.First
        else
            arr[selectedItem.value!!.rows()][selectedItem.value!!.column()]++

        _arrowsFieldArray.value = arr

        /*??? поч не работает
        if(_arrowsFieldArray.value!![selectedItem.value!!.rows()][selectedItem.value!!.column()] == Positions.Last)
            _arrowsFieldArray.value!![selectedItem.value!!.rows()][selectedItem.value!!.column()] = Positions.First
        else
            _arrowsFieldArray.value!![selectedItem.value!!.rows()][selectedItem.value!!.column()]++
        */
    }

    fun step(){
        val arr = _arrowsFieldArray.value!!

        for(i in arrowsFieldArray.value!!.indices){
            for(j in arrowsFieldArray.value!!.indices){
                when(arrowsFieldArray.value!![i][j]){
                    Arrow.UP.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i - 1)][j]
                    Arrow.UP_RIGHT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i - 1)][check(j + 1)]
                    Arrow.RIGHT.position ->
                        arr[i][j] = arrowsFieldArray.value!![i]                 [check(j + 1)]
                    Arrow.DOWN_RIGHT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i + 1)][check(j + 1)]
                    Arrow.DOWN.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i + 1)][j]
                    Arrow.DOWN_LEFT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i + 1)][check(j - 1)]
                    Arrow.LEFT.position ->
                        arr[i][j] = arrowsFieldArray.value!![i]                 [check(j - 1)]
                    Arrow.UP_LEFT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i - 1)][check(j - 1)]
                }
            }
        }
        _arrowsFieldArray.value = arr
    }

    private fun check(index: Int): Int {
        return when (index) {
            -1                  -> Constants.SPANCOUNT - 1
            Constants.SPANCOUNT -> 0
            else                -> index
        }
    }

}