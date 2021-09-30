package com.example.arrow.presentation.recycler.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arrow.R
import com.example.arrow.databinding.ListItemArrowBinding
import com.example.arrow.domain.models.arrow.Arrow

class ArrowsHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = ListItemArrowBinding.bind(view)

    fun bind(arrowsItem: Int, border: Int) {
        binding.imgvArrow.setBackgroundResource(border)
        binding.imgvArrow.setOnClickListener {  }
        setArrowsItem(arrowsItem)
    }

    private fun setArrowsItem(arrowsItemValue: Int) {
        for (positions in Arrow.values()) {
            if (positions.position == arrowsItemValue) {
                binding.imgvArrow.setImageResource(positions.imgArrow)
            }
        }
    }

    companion object {

        fun create(
            parent: ViewGroup
        ): ArrowsHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_arrow, parent, false
            )

            //val layoutParams = view.layoutParams
            //layoutParams.width = 50
            //layoutParams.height = 50
            //view.layoutParams = layoutParams

            return ArrowsHolder(
                view = view
            )
        }

    }

}