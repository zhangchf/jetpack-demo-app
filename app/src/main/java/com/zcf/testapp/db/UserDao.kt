package com.zcf.testapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zcf.testapp.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertAll(vararg users: User)

    @Delete
    suspend fun delete(vararg users: User)

    @Query("select * from User")
    fun getAll(): Flow<List<User>>
}