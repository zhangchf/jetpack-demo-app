package com.zcf.testapp.fragment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zcf.testapp.databinding.FragmentBottomNav1Binding

class BottomNav1Fragment: Fragment() {

    private lateinit var binding: FragmentBottomNav1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomNav1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = MyViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabTitle = when (position) {
                0 -> "Tab-A "
                1 -> "Tab-B"
                else -> "Invalid position"
            }
            tab.text = tabTitle
        }.attach()
    }
}


