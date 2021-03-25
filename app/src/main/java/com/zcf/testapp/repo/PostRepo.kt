package com.zcf.testapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.zcf.testapp.base.ResultWrapper
import com.zcf.testapp.db.PostDao
import com.zcf.testapp.model.Post
import com.zcf.testapp.web.TypicodeApi
import com.zcf.testapp.web.TypicodeService
import java.lang.Exception
import javax.inject.Inject

class PostRepo @Inject constructor(
        val typicodeService: TypicodeService,
        val postDao: PostDao
) {

    fun getPosts() = liveData<ResultWrapper<List<Post>>> {
        emitSource(
                postDao.getAll().map { ResultWrapper.Success(it) }
        )
        val result = typicodeService.getPosts()
        if (result is ResultWrapper.Success) {
            postDao.deleteAll()
            postDao.insertAll(*result.value.toTypedArray())
        } else {
            emit(result)
        }
    }

}