package com.example.arrow.presentation.screens.arrows_field_control

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arrow.databinding.FragmentArrowsFieldControlBinding
import com.example.arrow.presentation.screens.shared_view_model.ArrowsFieldViewModel

class ArrowsFieldControlFragment : Fragment() {

    companion object {
        fun newInstance() = ArrowsFieldControlFragment()
    }

    //private var _bindingField: FragmentArrowsFieldBinding? = null
    //private val bindingField get() = _bindingField!!

    private var _bindingControl: FragmentArrowsFieldControlBinding? = null
    private val bindingControl get() = _bindingControl!!

    private val sharedViewModel: ArrowsFieldViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingControl = FragmentArrowsFieldControlBinding.inflate(inflater, container, false)
        return bindingControl.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        bindingControl.btnRotate.setOnClickListener {
            sharedViewModel.changeOrientationOfSelectedItem()
        }
        bindingControl.btnGenerate.setOnClickListener {
            sharedViewModel.setArrowsFieldArray()
        }
        bindingControl.btnStep.setOnClickListener {
            sharedViewModel.step()
        }
    }

}
