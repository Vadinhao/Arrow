package com.example.arrow.presentation.screens.arrows_field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arrow.databinding.FragmentArrowsFieldBinding
import com.example.arrow.domain.models.position.Position
import com.example.arrow.presentation.recycler.adapter.ArrowsAdapter
import com.example.arrow.presentation.screens.shared_view_model.ArrowsFieldViewModel


class ArrowsFieldFragment : Fragment() {

    companion object {
        fun newInstance() = ArrowsFieldFragment()
    }

    private var _bindingField: FragmentArrowsFieldBinding? = null
    private val bindingField get() = _bindingField!!

    private val sharedViewModel: ArrowsFieldViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingField = FragmentArrowsFieldBinding.inflate(inflater, container, false)
        return bindingField.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpVM()
        setUpUi()
    }

    private fun setUpUi() {
        bindingField.rvArrows.adapter = ArrowsAdapter(
            sharedViewModel.selectedItem,
            sharedViewModel.arrowsFieldArray,
            sharedViewModel)
    }

    private fun setUpVM(){
        sharedViewModel.setSelectedItem(Position(0))
        sharedViewModel.setArrowsFieldArray()
        //on select item
        sharedViewModel.selectedItem.observe(viewLifecycleOwner,
            { newSelection ->
                bindingField.rvArrows.adapter!!.notifyDataSetChanged()
            })
        //on click btn_rotate
        sharedViewModel.arrowsFieldArray.observe(viewLifecycleOwner,
            { changedItem ->
                bindingField.rvArrows.adapter!!.notifyItemChanged(sharedViewModel.selectedItem.value!!.getPosition())
            })
        //on click btn_generate, btn_step
        sharedViewModel.arrowsFieldArray.observe(viewLifecycleOwner,
            { changedArr ->
                bindingField.rvArrows.adapter!!.notifyDataSetChanged()
            })
    }

}