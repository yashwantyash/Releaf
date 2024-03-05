package com.releaf.releaf.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.releaf.releaf.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertUser(user: User)

//    @Update
//    suspend fun updateUser(user: UserRoom)

    @Delete
    suspend fun deleteUser(user: User)

//    @Query("SELECT COUNT(*) FROM USER_TABLE")
//    suspend fun getUserCount(): Int

//    @Query("SELECT * FROM USER_TABLE")
//    fun getUser(): LiveData<List<UserRoom>>
}