package com.zcf.testapp.db

import androidx.room.*
import com.zcf.testapp.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg posts: Post)

    @Delete
    suspend fun delete(vararg posts: Post)

    @Query("select * from Post")
    fun getAll(): Flow<List<Post>>
}