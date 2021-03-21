package com.zcf.testapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zcf.testapp.databinding.FragmentTab1Binding

class Tab1Fragment: Fragment() {

    private lateinit var binding: FragmentTab1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTab1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textContent.setOnClickListener {
            navigateToDetailScreen(0)
        }
    }

    private fun navigateToDetailScreen(contentId: Int) {
        val direction = BottomNav1FragmentDirections
            .actionBottomNav1FragmentToBottomNav4Fragment()
            .setContentId(contentId)
        findNavController().navigate(direction)
    }
}