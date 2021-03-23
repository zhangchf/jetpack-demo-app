package com.zcf.testapp.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zcf.testapp.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert
    suspend fun insertAll(vararg posts: Post)

    @Delete
    suspend fun delete(vararg posts: Post)

    @Query("select * from Post")
    fun getAll(): Flow<List<Post>>
}