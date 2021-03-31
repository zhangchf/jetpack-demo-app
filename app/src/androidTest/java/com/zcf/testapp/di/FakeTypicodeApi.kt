package com.zcf.testapp.di

import com.zcf.testapp.model.Post
import com.zcf.testapp.model.User
import com.zcf.testapp.web.TypicodeApi
import javax.inject.Inject

class FakeTypicodeApi @Inject constructor() : TypicodeApi {
    override suspend fun getPosts(): List<Post> {
        return listOf(
            Post(1, 100, "Post 1", "Post 1 body"),
            Post(2, 102, "Post 2", "Post 2 body"),
            Post(3, 103, "Post 3", "Post 3 body")
        )
    }

    override suspend fun getUsers(): List<User> {
        return listOf()
    }
}