package com.example.arrow.domain.models.arrow

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import com.example.arrow.R

enum class Arrow(
    @IntegerRes val position: Int,
    @DrawableRes val imgArrow: Int
) {
    UP(1, R.drawable.ic_arrow_up),
    UP_RIGHT(2, R.drawable.ic_arrow_right_up),
    RIGHT(3, R.drawable.ic_arrow_right),
    DOWN_RIGHT(4, R.drawable.ic_arrow_right_down),
    DOWN(5, R.drawable.ic_arrow_down),
    DOWN_LEFT(6, R.drawable.ic_arrow_left_down),
    LEFT(7, R.drawable.ic_arrow_left),
    UP_LEFT(8, R.drawable.ic_arrow_left_up)
}

object Positions{
    val First = Arrow.values()[0].position
    val Last = Arrow.values()[Arrow.values().size - 1].position
}