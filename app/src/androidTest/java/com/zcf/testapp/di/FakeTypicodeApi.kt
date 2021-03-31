package com.zcf.testapp.di

import com.zcf.testapp.model.Post
import com.zcf.testapp.model.User
import com.zcf.testapp.web.TypicodeApi
import javax.inject.Inject

class FakeTypicodeApi @Inject constructor() : TypicodeApi {
    override suspend fun getPosts(): List<Post> {
        return listOf(0..100).flatten().map { i ->
            Post(i, 100+i, "Post $i", "Post $i body")
        }
    }

    override suspend fun getUsers(): List<User> {
        return listOf()
    }
}