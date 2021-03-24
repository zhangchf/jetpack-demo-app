package com.zcf.testapp.web

import com.zcf.testapp.model.Post
import com.zcf.testapp.model.User
import retrofit2.Response
import retrofit2.http.GET

interface TypicodeApi {

    companion object {
        const val URL_HOST = "https://jsonplaceholder.typicode.com/"
    }

    @GET("/posts")
    suspend fun getPosts() : List<Post>

    @GET("/users")
    suspend fun getUsers() : List<User>
}