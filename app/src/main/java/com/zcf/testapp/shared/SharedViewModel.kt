package com.zcf.testapp.shared

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zcf.testapp.base.ResultWrapper
import com.zcf.testapp.model.Post
import com.zcf.testapp.db.PostDao
import com.zcf.testapp.repo.PostRepo
import com.zcf.testapp.web.TypicodeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
        val postRepo: PostRepo
) : ViewModel() {

    val posts: LiveData<ResultWrapper<List<Post>>> = postRepo.getPosts()

    init {
        getUsers()
    }

    fun getUsers() {

        Log.i("zcf", "getdata begin")

//        viewModelScope.launch {
//            _posts.value = postRepo.getPosts()
//            Log.i("zcf", "getData end")
//
//            postDao.insertAll(*posts.toTypedArray())
//
//        }
    }

}