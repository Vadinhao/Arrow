package com.example.arrow.presentation.recycler.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arrow.R
import com.example.arrow.databinding.ListItemArrowBinding
import com.example.arrow.presentation.screens.arrows_field.ArrowsSelection

class ArrowsHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = ListItemArrowBinding.bind(view)

    fun bind(position : Int, selection: ArrowsSelection){
        when(selection){
            ArrowsSelection.SELECTED -> binding.imgvArrow.setBackgroundResource(R.drawable.image_selected_item_border)
            ArrowsSelection.UNSELECTED -> binding.imgvArrow.setBackgroundResource(R.drawable.image_item_border)
        }
        binding.imgvArrow.setImageResource(R.drawable.ic_arrow_right_up)
    }

    companion object {

        fun create(
            parent: ViewGroup
        ): ArrowsHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_arrow, parent, false
            )

            return ArrowsHolder(
                view = view
            )
        }

    }

}