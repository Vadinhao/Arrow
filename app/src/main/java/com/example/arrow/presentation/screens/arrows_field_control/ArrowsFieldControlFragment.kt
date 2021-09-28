package com.example.arrow.presentation.screens.arrows_field_control

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.arrow.R

class ArrowsFieldControlFragment : Fragment() {

    companion object {
        fun newInstance() = ArrowsFieldControlFragment()
    }

    private lateinit var viewModel: ArrowsFieldControlViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_arrows_field_control, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArrowsFieldControlViewModel::class.java)
        // TODO: Use the ViewModel
    }

}