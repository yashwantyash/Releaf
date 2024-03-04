package com.releaf.releaf.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.releaf.releaf.models.User
import com.releaf.releaf.models.UserRoom

interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserRoom)

    @Update
    suspend fun updateUser(user: UserRoom)

    @Delete
    suspend fun deleteUser(user: UserRoom)

    @Query("SELECT * FROM USER_TABLE")
    fun getUser(): LiveData<List<UserRoom>>
}