package com.example.arrow.presentation.recycler.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arrow.R
import com.example.arrow.databinding.ListItemArrowBinding
import com.example.arrow.domain.models.arrow.Arrow
import com.example.arrow.domain.models.position.Position
import com.example.arrow.presentation.screens.shared_view_model.ArrowsFieldViewModel

class ArrowsHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = ListItemArrowBinding.bind(view)

    fun bind(
        arrowsItemValue: Int,
        border: Int,
        position: Int,
        viewModel: ArrowsFieldViewModel
    ) {
        binding.imgvArrow.setBackgroundResource(border)
        binding.imgvArrow.setOnClickListener {
            viewModel.setSelectedItem(Position(position))
        }
        setArrowsItem(arrowsItemValue)
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