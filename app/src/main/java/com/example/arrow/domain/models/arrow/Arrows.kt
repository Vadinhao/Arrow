package com.example.arrow.domain.models.arrow

class Arrows(size: Int, numOfIteration: Int) {

    val arrows: Array<Array<Int>> = newRandomArray(size)
    val arrowsEmpty: Array<Array<Int>> = newEmptyArray(size)
    val arrowsProgress: Array<Array<Array<Int>>> = newArrayForProgress(size, numOfIteration)
    val arrowsEmptyProgress: Array<Array<Array<Int>>> =
        newEmptyArrayForProgress(size, numOfIteration)

    private fun newRandomArray(size: Int): Array<Array<Int>> {
        return Array(size) { Array(size) { (Positions.First..Positions.Last).random() } }
    }

    private fun newEmptyArray(size: Int): Array<Array<Int>> {
        return Array(size) { Array(size) { 0 } }
    }

    private fun newArrayForProgress(size: Int, numOfIteration: Int): Array<Array<Array<Int>>> {
        return Array(numOfIteration) { Array(size) { Array(size) { 0 } } }
    }

    private fun newEmptyArrayForProgress(size: Int, numOfIteration: Int): Array<Array<Array<Int>>> {
        return Array(numOfIteration) { Array(size) { Array(size) { (Positions.First..Positions.Last).random() } } }
    }

}