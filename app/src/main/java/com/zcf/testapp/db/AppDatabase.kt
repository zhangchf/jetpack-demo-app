package com.zcf.testapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zcf.testapp.model.Post
import com.zcf.testapp.model.User

@Database(entities = [Post::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun postDao(): PostDao

}