package com.zcf.testapp.fragment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zcf.testapp.R
import com.zcf.testapp.databinding.FragmentTab1Binding
import com.zcf.testapp.databinding.FragmentTab2Binding

class Tab2Fragment: Fragment() {
    private lateinit var binding: FragmentTab2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textContent.setOnClickListener {
            navigateToDetailScreen(0)
        }
    }

    private fun navigateToDetailScreen(contentId: Int) {
        val direction = BottomNav1FragmentDirections.actionBottomNav1FragmentToBottomNav4Fragment()
            .setContentId(contentId)
        findNavController().navigate(direction)
    }
}