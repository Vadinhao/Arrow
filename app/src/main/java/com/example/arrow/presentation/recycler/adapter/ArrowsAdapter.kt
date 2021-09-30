package com.example.arrow.presentation.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arrow.constants.Constants
import com.example.arrow.domain.models.arrow.Arrows
import com.example.arrow.presentation.recycler.holder.ArrowsHolder
import com.example.arrow.presentation.screens.arrows_field.ArrowsSelection
import kotlin.math.pow

class ArrowsAdapter(selectedItem: Int) : RecyclerView.Adapter<ArrowsHolder>() {

    private val arrowsData = Arrows(Constants.SPANCOUNT).arrows
    private val selectedItem = selectedItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrowsHolder {
        return ArrowsHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArrowsHolder, position: Int) {
        val row = position / Constants.SPANCOUNT
        val column = position % Constants.SPANCOUNT
        val isSelectedBorder: Int = if(selectedItem == position) ArrowsSelection.SELECTED.border else ArrowsSelection.UNSELECTED.border
        holder.bind(arrowsData[row][column], isSelectedBorder)
    }

    override fun getItemCount(): Int {
        return arrowsData.size.toDouble().pow(2.0).toInt()
    }
}