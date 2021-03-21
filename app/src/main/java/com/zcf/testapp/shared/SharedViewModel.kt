package com.zcf.testapp.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _dataSet = MutableLiveData<List<Int>>(listOf())

    val dataSet: LiveData<List<Int>> = _dataSet

}