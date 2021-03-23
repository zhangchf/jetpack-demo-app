package com.zcf.testapp.shared

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zcf.testapp.model.Post
import com.zcf.testapp.db.PostDao
import com.zcf.testapp.web.TypicodeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    val typicodeApi: TypicodeApi,
    val postDao: PostDao
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>(listOf())
    val posts: LiveData<List<Post>> = _posts

    init {
        getUsers()
    }

    fun getUsers() {

        Log.i("zcf", "getdata begin")

        viewModelScope.launch {
            val posts = typicodeApi.getPosts()
            _posts.value =posts
            Log.i("zcf", "getData end")

            postDao.insertAll(*posts.toTypedArray())

        }
    }

}