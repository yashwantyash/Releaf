package com.releaf.releaf.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.releaf.releaf.utility.Constants.CHECKIN_TABLE
import com.releaf.releaf.utility.Constants.JOURNAL_TABLE

@Entity(tableName = JOURNAL_TABLE)
data class JournalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,

)

data class JournalCheckBoxModel(
    var triggers: String?,
    var mood: String?,
    var progress: String?
)
