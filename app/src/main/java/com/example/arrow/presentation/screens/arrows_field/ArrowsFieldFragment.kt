package com.example.arrow.presentation.screens.arrows_field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.arrow.databinding.FragmentArrowsFieldBinding
import com.example.arrow.domain.models.position.Position
import com.example.arrow.presentation.recycler.adapter.ArrowsAdapter


class ArrowsFieldFragment : Fragment() {

    companion object {
        fun newInstance() = ArrowsFieldFragment()
    }

    private var _binding: FragmentArrowsFieldBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ArrowsFieldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArrowsFieldBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpVM()
        setUpUi()
    }

    private fun setUpUi() {
        binding.rvArrows.adapter = ArrowsAdapter(
            viewModel.selectedItem,
            viewModel.arrowsFieldArray,
            viewModel)
    }

    private fun setUpVM(){
        viewModel = ViewModelProvider(this).get(ArrowsFieldViewModel::class.java)
        viewModel.setSelectedItem(Position(0))
        viewModel.selectedItem.observe(viewLifecycleOwner,
            { newSelection ->
                binding.rvArrows.adapter!!.notifyDataSetChanged()
            })
    }

}