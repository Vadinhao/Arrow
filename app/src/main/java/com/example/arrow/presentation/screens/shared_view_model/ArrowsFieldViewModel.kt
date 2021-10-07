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

    private var _init: Boolean = true
    val init: Boolean get() = _init

    private var _selectedItem = MutableLiveData<Position>()
    val selectedItem: LiveData<Position> get() = _selectedItem

    private var _arrowsFieldArray = MutableLiveData<Array<Array<Int>>>()
    val arrowsFieldArray: LiveData<Array<Array<Int>>> get() = _arrowsFieldArray

    private var _iterationNum = MutableLiveData<Int>(30)
    val iterationNum: LiveData<Int> = _iterationNum

    fun initVM() {
        _init = false
    }

    fun nextIter() {
        _iterationNum.value = iterationNum.value!! + 1
    }

    fun clearIter() {
        _iterationNum.value = 0
    }

    fun setSelectedItem(selectedItem: Position) {
        _selectedItem.value = selectedItem
    }

    fun setArrowsFieldArray() {
        _arrowsFieldArray.value = Arrows(Constants.SPANCOUNT).arrows
    }

    fun changeOrientationOfSelectedItem() {
        val arr = arrowsFieldArray.value!!

        if (arr[selectedItem.value!!.rows()][selectedItem.value!!.column()] == Positions.Last)
            arr[selectedItem.value!!.rows()][selectedItem.value!!.column()] = Positions.First
        else
            arr[selectedItem.value!!.rows()][selectedItem.value!!.column()]++

        //костыль, не работает обзервер
        _arrowsFieldArray.value = arr
        /*??? поч не работает
        if(_arrowsFieldArray.value!![selectedItem.value!!.rows()][selectedItem.value!!.column()] == Positions.Last)
            _arrowsFieldArray.value!![selectedItem.value!!.rows()][selectedItem.value!!.column()] = Positions.First
        else
            _arrowsFieldArray.value!![selectedItem.value!!.rows()][selectedItem.value!!.column()]++
        */
    }

    fun step() {
        var arr: Array<Array<Int>> = Arrows(Constants.SPANCOUNT).arrowsEmpty
        copy(arrowsFieldArray.value!!, 0, arr, 0, Constants.SPANCOUNT)

        for (i in arr.indices) {
            for (j in arr.indices) {
                when (arr[i][j]) {
                    Arrow.UP.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i - 1)][j]
                    Arrow.UP_RIGHT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i - 1)][check(j + 1)]
                    Arrow.RIGHT.position ->
                        arr[i][j] = arrowsFieldArray.value!![i][check(j + 1)]
                    Arrow.DOWN_RIGHT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i + 1)][check(j + 1)]
                    Arrow.DOWN.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i + 1)][j]
                    Arrow.DOWN_LEFT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i + 1)][check(j - 1)]
                    Arrow.LEFT.position ->
                        arr[i][j] = arrowsFieldArray.value!![i][check(j - 1)]
                    Arrow.UP_LEFT.position ->
                        arr[i][j] = arrowsFieldArray.value!![check(i - 1)][check(j - 1)]
                }
            }
        }

        copy(arr, 0, arrowsFieldArray.value!!, 0, Constants.SPANCOUNT)
        //костыль, не работает обзервер
        _arrowsFieldArray.value = arr
    }

    private fun check(index: Int): Int {
        return when (index) {
            -1 -> Constants.SPANCOUNT - 1
            Constants.SPANCOUNT -> 0
            else -> index
        }
    }

    private fun copy(
        arrayFrom: Array<Array<Int>>, posBegFrom: Int,
        arrayTo: Array<Array<Int>>, posBegTo: Int,
        length: Int
    ) {
        for (i in posBegTo until length) {
            for (j in posBegFrom until length) {
                arrayTo[i][j] = arrayFrom[i][j]
            }
        }
    }

}