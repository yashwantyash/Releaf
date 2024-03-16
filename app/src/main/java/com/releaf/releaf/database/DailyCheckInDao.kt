package com.releaf.releaf.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.releaf.releaf.models.CheckInModel
import kotlinx.coroutines.flow.Flow


@Dao
interface DailyCheckInDao {
    @Insert
    suspend fun insertCheckIn(checkInMode: CheckInModel)

    @Query("SELECT COUNT(*) FROM CHECKIN_TABLE")
    suspend fun getCheckInCount(): Int

    @Query("SELECT * FROM CHECKIN_TABLE order by checkInDate ASC")
    fun  getCheckInDate(): Flow<List<CheckInModel>>

}

