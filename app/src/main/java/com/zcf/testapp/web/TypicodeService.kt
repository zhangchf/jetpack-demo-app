package com.zcf.testapp.web

import com.zcf.testapp.base.NetworkException
import com.zcf.testapp.base.ResultWrapper
import com.zcf.testapp.model.Post
import javax.inject.Inject

class TypicodeService @Inject constructor(
    val typicodeApi: TypicodeApi
) {

//    suspend fun getPosts(): ResultWrapper<List<Post>> {
//        val response = typicodeApi.getPosts()
//        return if (response.isSuccessful && response.body() != null) {
//            ResultWrapper.Success(response.body()!!)
//        } else {
//            ResultWrapper.Error(response.code(), response.message())
//        }
//    }
}