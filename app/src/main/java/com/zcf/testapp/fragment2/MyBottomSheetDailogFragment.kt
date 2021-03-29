package com.zcf.testapp.fragment2

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zcf.testapp.databinding.FragmentBottomSheetBinding

class MyBottomSheetDailogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        Log.i("zcflog", "dialog cancelled")
    }
}