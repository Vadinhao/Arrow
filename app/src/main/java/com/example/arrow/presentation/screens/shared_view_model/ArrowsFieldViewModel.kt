package com.example.arrow.presentation.screens.shared_view_model

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arrow.constants.Constants
import com.example.arrow.domain.models.arrow.Arrow
import com.example.arrow.domain.models.arrow.Arrows
import com.example.arrow.domain.models.arrow.Positions
import com.example.arrow.domain.models.position.Position
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ArrowsFieldViewModel : ViewModel() {

    private var _init: Boolean = true
    val init: Boolean get() = _init

    private var _selectedItem = MutableLiveData<Position>()
    val selectedItem: LiveData<Position> get() = _selectedItem

    private var _arrowsFieldArray = MutableLiveData<Array<Array<Int>>>()
    val arrowsFieldArray: LiveData<Array<Array<Int>>> get() = _arrowsFieldArray

    private var _arrowsProgressFieldArray = MutableLiveData<Array<Array<Array<Int>>>>()
    val arrowsProgressFieldArray: LiveData<Array<Array<Array<Int>>>> get() = _arrowsProgressFieldArray

    //текущая итерация
    private var _iterationNum = MutableLiveData<Int>(Constants.STARTITERATIONNUM)
    val iterationNum: LiveData<Int> = _iterationNum

    private var _numOfIteration = MutableLiveData<Int>()
    val numOfIteration: LiveData<Int> = _numOfIteration

    private var _sizeOfListItem: Int = 0
    val sizeOfListItem get() = _sizeOfListItem

    private var _SPANCOUNT = MutableLiveData<Int>(Constants.SPANCOUNT)
    val SPANCOUNT: LiveData<Int> = _SPANCOUNT

    //fun Int.toDp() = (this * Resources.getSystem().displayMetrics.density).toInt()
    fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun setSizeOfListItem(size: Int){
        _sizeOfListItem = size.toPx()
    }

    fun initVM() {
        _init = false
    }

    fun clearIter() {
        _iterationNum.value = Constants.STARTITERATIONNUM
    }

    fun setIterationNum(position: Int) {
        _iterationNum.value = position
    }

    fun setSpanCount(spanCount: Int){
        _SPANCOUNT.value = spanCount
    }

    fun setArrowsFieldArray() {
        _arrowsFieldArray.value = Arrows(SPANCOUNT.value!!, numOfIteration.value!! + 1).arrows
    }

    fun setArrowsProgressField() {
        _arrowsProgressFieldArray.value =
            Arrows(SPANCOUNT.value!!, numOfIteration.value!! + 1).arrowsEmptyProgress
        copy(
            _arrowsFieldArray.value!!,
            0,
            _arrowsProgressFieldArray.value!![0],
            0,
            _arrowsFieldArray.value!!.size
        )
    }

    fun setNumOfIteration(num: Int) {
        _numOfIteration.value = num
    }

    fun setSelectedItem(selectedItem: Position) {
        _selectedItem.value = selectedItem
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

    fun playAnimated(waitMilliseconds: Int) = runBlocking {
        viewModelScope.launch {

            for (i in arrowsProgressFieldArray.value!!.indices) {
                //_arrowsFieldArray.postValue(_arrowsProgressFieldArray.value!![i])
                //_iterationNum.postValue(i)
                setField(i)
                setIterationNum(i)
                delay(waitMilliseconds.toLong())
            }
        }
    }

    fun setField(pos: Int) {
        _arrowsFieldArray.value = _arrowsProgressFieldArray.value!![pos]
    }

    fun generateProgress() {
        val arr: Array<Array<Array<Int>>> =
            Arrows(SPANCOUNT.value!!, numOfIteration.value!! + 1).arrowsEmptyProgress
        copy(_arrowsProgressFieldArray.value!![0], 0, arr[0], 0, arrowsFieldArray.value!!.size)
        for (k in 1..numOfIteration.value!!) {
            arr[k] = step(arr[k - 1])
            copy(arr[k], 0, _arrowsProgressFieldArray.value!![k], 0, arrowsFieldArray.value!!.size)
        }

    }

    private fun step(arrBefore: Array<Array<Int>>): Array<Array<Int>> {
        var arr: Array<Array<Int>> = Arrows(SPANCOUNT.value!!, numOfIteration.value!!).arrowsEmpty
        copy(arrBefore, 0, arr, 0, SPANCOUNT.value!!)

        for (i in arr.indices) {
            for (j in arr.indices) {
                when (arr[i][j]) {
                    Arrow.UP.position ->
                        arr[i][j] = arrBefore[check(i - 1)][j]
                    Arrow.UP_RIGHT.position ->
                        arr[i][j] = arrBefore[check(i - 1)][check(j + 1)]
                    Arrow.RIGHT.position ->
                        arr[i][j] = arrBefore[i][check(j + 1)]
                    Arrow.DOWN_RIGHT.position ->
                        arr[i][j] = arrBefore[check(i + 1)][check(j + 1)]
                    Arrow.DOWN.position ->
                        arr[i][j] = arrBefore[check(i + 1)][j]
                    Arrow.DOWN_LEFT.position ->
                        arr[i][j] = arrBefore[check(i + 1)][check(j - 1)]
                    Arrow.LEFT.position ->
                        arr[i][j] = arrBefore[i][check(j - 1)]
                    Arrow.UP_LEFT.position ->
                        arr[i][j] = arrBefore[check(i - 1)][check(j - 1)]
                }
            }
        }
        return arr
    }

    private fun check(index: Int): Int {
        return when (index) {
            -1 -> SPANCOUNT.value!! - 1
            SPANCOUNT.value!! -> 0
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