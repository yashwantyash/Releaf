package com.releaf.releaf.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.releaf.releaf.models.CheckInModel
import com.releaf.releaf.models.JournalModel
import kotlinx.coroutines.flow.Flow


@Dao
interface JournalDao {
    @Insert
    suspend fun insertJournal(journal: JournalModel)

    @Query("SELECT COUNT(*) FROM JOURNAL_TABLE")
    suspend fun getJournalCount(): Int

//    @Query("SELECT * FROM JOURNAL_TABLE order by date ASC")
//    fun  getAllJournalByDate(): Flow<List<CheckInModel>>

//    @Query("SELECT * FROM USER_TABLE")
//    fun getAllJournal(): LiveData<List<Journal>>

}

