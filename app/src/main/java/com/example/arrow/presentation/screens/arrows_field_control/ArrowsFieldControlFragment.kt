package com.example.arrow.presentation.screens.arrows_field_control

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arrow.databinding.FragmentArrowsFieldBinding
import com.example.arrow.databinding.FragmentArrowsFieldControlBinding
import com.example.arrow.presentation.screens.shared_view_model.ArrowsFieldViewModel


class ArrowsFieldControlFragment : Fragment() {

    private var _bindingControl: FragmentArrowsFieldControlBinding? = null
    private val bindingControl get() = _bindingControl!!

    private var _bindingField: FragmentArrowsFieldBinding? = null
    private val bindingField get() = _bindingField!!

    private val sharedViewModel: ArrowsFieldViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingControl = FragmentArrowsFieldControlBinding.inflate(inflater, container, false)
        _bindingField = FragmentArrowsFieldBinding.inflate(inflater, container, false)
        bindingControl.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            controlFragment = this@ArrowsFieldControlFragment
        }

        return bindingControl.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {

        bindingControl.seekBar.isEnabled = false

        bindingControl.btnRotate.setOnClickListener {
            sharedViewModel.changeOrientationOfSelectedItem()
            bindingControl.seekBar.isEnabled = false
        }

        bindingControl.btnGenerate.setOnClickListener {
            sharedViewModel.clearIter()
            sharedViewModel.setArrowsFieldArray()
            bindingControl.seekBar.isEnabled = false
        }

        bindingControl.btnGenerateProgress.setOnClickListener {
            sharedViewModel.setArrowsProgressField()
            sharedViewModel.generateProgress()
            sharedViewModel.setIterationNum(0)
            bindingControl.seekBar.progress = 0
            bindingControl.seekBar.isEnabled = true
        }

        bindingControl.btnPlayAnimated.setOnClickListener {

        }

        bindingControl.edtNumberOfIteration.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    bindingControl.tvMaxNumberOfIteration.text = s
                    sharedViewModel.setNumOfIteration(s.toString().toInt())
                    bindingControl.seekBar.isEnabled = false
                }
            }
        })

        bindingControl.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                sharedViewModel.setIterationNum(progress)
                sharedViewModel.setField(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

}