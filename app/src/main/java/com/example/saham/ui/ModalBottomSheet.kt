package com.example.saham.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.saham.R
import com.example.saham.databinding.BottomSheetBinding
import com.example.saham.interfaces.BottomSheetHandler
import com.example.saham.viewmodels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment(), BottomSheetHandler {
    private lateinit var binding: BottomSheetBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.handler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onSelectNewAddress(index: Int) {
        viewModel.setSelectedAddress(index)
        dismiss()
    }

    override fun onSelectAddress() {
        dismiss()
        findNavController().navigate(R.id.mapFragment)
    }
}