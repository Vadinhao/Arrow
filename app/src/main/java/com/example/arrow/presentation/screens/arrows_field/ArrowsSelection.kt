package com.example.arrow.presentation.screens.arrows_field

sealed interface ArrowsSelection{
    object SELECTED: ArrowsSelection
    object UNSELECTED: ArrowsSelection
}