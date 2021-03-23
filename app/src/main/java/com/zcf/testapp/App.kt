package com.zcf.testapp

import android.app.Application
import androidx.room.Room
import com.zcf.testapp.repo.PostDatabase

class App : Application() {

    lateinit var postDb: PostDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        initDatabase()
    }

    fun initDatabase(){

        postDb = Room.databaseBuilder(this, PostDatabase::class.java, "post-db").build()
    }
}