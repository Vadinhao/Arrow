package com.example.arrow.presentation.screens.arrows_field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.arrow.R

class ArrowsFieldFragment : Fragment() {

    companion object {
        fun newInstance() = ArrowsFieldFragment()
    }

    private lateinit var viewModel: ArrowsFieldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_arrows_field, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArrowsFieldViewModel::class.java)
        // TODO: Use the ViewModel
    }

}