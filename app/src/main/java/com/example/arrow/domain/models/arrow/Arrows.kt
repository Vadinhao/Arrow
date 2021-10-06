package com.example.arrow.domain.models.arrow

class Arrows(size: Int) {

    val arrows: Array<Array<Int>> = newRandomArray(size)

    private fun newRandomArray(size: Int): Array<Array<Int>> {
        return Array(size) { Array(size) { (Positions.First..Positions.Last).random() } }
    }
}

