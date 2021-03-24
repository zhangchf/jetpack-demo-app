package com.zcf.testapp.repo

import androidx.lifecycle.liveData
import com.zcf.testapp.db.PostDao
import com.zcf.testapp.model.Post
import com.zcf.testapp.web.TypicodeApi
import javax.inject.Inject

class PostRepo @Inject constructor(
    val typicodeApi: TypicodeApi,
    val postDao: PostDao
) {
    var startIndex = 0

    fun getPosts() = liveData<List<Post>> {
        emitSource(postDao.getAll())
        val posts = typicodeApi.getPosts().subList(startIndex, startIndex + 10)
        startIndex += 10
        postDao.deleteAll()
        postDao.insertAll(*posts.toTypedArray())
    }

}