package com.zcf.testapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.zcf.testapp.databinding.FragmentBottomNav4Binding

class BottomNav4Fragment: Fragment(R.layout.fragment_bottom_nav_4) {

    private lateinit var binding: FragmentBottomNav4Binding

    val args: BottomNav4FragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomNav4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textContent.text = "Bottom 4 Content: ${args.contentId}"
    }
}