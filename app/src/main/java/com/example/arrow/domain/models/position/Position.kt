package com.example.arrow.domain.models.position

import com.example.arrow.constants.Constants

class Position(
    private val position: Int
) {
    fun rows() = position / Constants.SPANCOUNT
    fun column() = (position % Constants.SPANCOUNT)
    fun getPosition() = position
}