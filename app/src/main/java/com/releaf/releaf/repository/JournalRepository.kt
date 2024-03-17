//package com.releaf.releaf.repository
//
//import com.releaf.releaf.database.JournalDao
//import com.releaf.releaf.models.journal.Journal
//
//class JournalRepository(private val journalDao: JournalDao) {
//    suspend fun getAllJournalByDate(): List<Journal> {
//        return journalDao.getAllJournalByDate()
//    }
//}