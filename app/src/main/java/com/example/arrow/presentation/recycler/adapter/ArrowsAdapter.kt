package com.example.arrow.presentation.recycler.adapter

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.arrow.constants.Constants
import com.example.arrow.domain.models.position.Position
import com.example.arrow.presentation.recycler.holder.ArrowsHolder
import com.example.arrow.presentation.screens.arrows_field.ArrowsSelection
import com.example.arrow.presentation.screens.shared_view_model.ArrowsFieldViewModel
import kotlin.math.pow

class ArrowsAdapter(
    private val selectedItem: LiveData<Position>,
    private val arrowsData: LiveData<Array<Array<Int>>>,
    private val viewModel: ArrowsFieldViewModel
) : RecyclerView.Adapter<ArrowsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrowsHolder {
        return ArrowsHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArrowsHolder, position: Int) {
        val isSelectedBorder: Int =
            if (selectedItem.value!!.getPosition() == position)
                ArrowsSelection.SELECTED.border
            else
                ArrowsSelection.UNSELECTED.border
        holder.bind(
            arrowsData.value!![Position(position).rows()][Position(position).column()],
            isSelectedBorder,
            position,
            //костыль*
            viewModel
        )
    }

    override fun getItemCount(): Int {
        return Constants.SPANCOUNT.toDouble().pow(2.0).toInt()
    }
}