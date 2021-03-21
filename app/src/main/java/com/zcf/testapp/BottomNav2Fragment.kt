package com.zcf.testapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zcf.testapp.databinding.FragmentBottomNav2Binding

class BottomNav2Fragment: Fragment() {

    private lateinit var binding: FragmentBottomNav2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomNav2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textContent.setOnClickListener {
            navigateToDetailsScreen(2)
        }
    }

    private fun navigateToDetailsScreen(contentId: Int) {
        val direction = BottomNav2FragmentDirections
                .actionBottomNav2FragmentToBottomNav4Fragment()
                .setContentId(contentId)
        findNavController().navigate(direction)
    }
}