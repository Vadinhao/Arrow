package com.example.arrow.presentation.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arrow.constants.Constants
import com.example.arrow.presentation.recycler.holder.ArrowsHolder
import com.example.arrow.presentation.screens.arrows_field.ArrowsSelection

class ArrowsAdapter : RecyclerView.Adapter<ArrowsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrowsHolder {
        return ArrowsHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArrowsHolder, position: Int) {
        holder.bind(position, selection = ArrowsSelection.UNSELECTED)
    }

    override fun getItemCount(): Int {
        return Constants.ARROWSNUMBER
    }
}
/*


when(selection){
    ArrowsSelection.SELECTED -> view.setBackgroundResource(R.drawable.image_selected_item_border)
    ArrowsSelection.UNSELECTED -> view.setBackgroundResource(R.drawable.image_item_border)
}
 */