package com.zcf.testapp.shared

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.zcf.testapp.App
import com.zcf.testapp.model.Post
import com.zcf.testapp.web.TypicodeApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val _posts = MutableLiveData<List<Post>>(listOf())

    val posts: LiveData<List<Post>> = _posts

    init {
        getUsers()
    }

    fun getUsers() {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(TypicodeApi.URL_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.i("zcf", "getdata begin")
        viewModelScope.launch {
            val posts = retrofit.create(TypicodeApi::class.java).getPosts()
            _posts.value =posts
            Log.i("zcf", "getData end")

            getApplication<App>().postDb.postDao().insertAll(*posts.toTypedArray())

        }
    }

}