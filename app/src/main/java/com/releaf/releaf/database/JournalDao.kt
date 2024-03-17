package com.releaf.releaf.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.releaf.releaf.models.journal.Journal


@Dao
interface JournalDao {
    @Insert
    suspend fun insertJournal(journal: Journal)

    @Query("SELECT COUNT(*) FROM JOURNAL_TABLE")
    suspend fun getJournalCount(): Int

    @Query("SELECT * FROM JOURNAL_TABLE order by date DESC")
    fun  getAllJournalByDate():  List<Journal>

//    @Query("SELECT * FROM USER_TABLE")
//    fun getAllJournal(): LiveData<List<Journal>>

}

