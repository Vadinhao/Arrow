package com.example.arrow.domain.models.arrow

class Arrows(size: Int) {

    private val _arrows: Array<Array<Int>> = newRandomArray(size)
    val arrows: Array<Array<Int>> get() = _arrows

    private fun newRandomArray(size: Int): Array<Array<Int>> {
        return Array(size) { Array(size) { (Positions.First..Positions.Last).random() } }
    }
}

