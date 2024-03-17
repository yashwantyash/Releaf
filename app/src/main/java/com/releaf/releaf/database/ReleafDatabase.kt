package com.releaf.releaf.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.releaf.releaf.models.CheckInModel
import com.releaf.releaf.models.journal.Journal
import com.releaf.releaf.models.User

@Database(entities = [User::class, CheckInModel::class, Journal::class], version = 3)
abstract class ReleafDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun dailyCheckInDao(): DailyCheckInDao
    abstract fun journalDao(): JournalDao

    companion object {
        private var DB_INSTANCE: ReleafDatabase? = null
        fun getDatabase(context: Context): ReleafDatabase {
            @Volatile
            /*Volatile ensure that as any value is assigned to this Instant variable,
            All threads will be informed about The updated value             */

            if (DB_INSTANCE == null) {
                synchronized(this){
                    /*synchronised (locking) is used to avoid two threads creating this database object at same time,
                    protect creation of two database instances*/
                    DB_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ReleafDatabase::class.java,
                        "releaf_db"
                    )
                        .build()
                }
            }
            return DB_INSTANCE!!
        }
    }
}