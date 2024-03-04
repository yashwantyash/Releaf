package com.releaf.releaf.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.releaf.releaf.models.UserRoom

@Database(entities = [UserRoom::class /*CheckIn::class*/], version = 1)
abstract class ReleafDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
//    abstract fun getCheckInDao(): UserDao

    companion object {
        @Volatile
        private var instance: ReleafDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ReleafDatabase::class.java,
                "releaf_db"
            ).build()
    }
}