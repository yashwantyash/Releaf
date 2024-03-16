package com.releaf.releaf.models

import androidx.lifecycle.ViewModel
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.releaf.releaf.utility.Constants.CHECKIN_TABLE
import com.releaf.releaf.utility.Constants.JOURNAL_TABLE

@Entity(tableName = JOURNAL_TABLE)
data class JournalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String?,
    val triggers: String? ,
    val mood: String?,
    val progress: String?,
    val aboutDay: String?,
    val title: String?,
    val description: String?
)

//data class JournalCheckBoxModel(
//    var triggers: String?,
//    var mood: String?,
//    var progress: String?
//)

class CheckBoxViewModel: ViewModel(){
    var triggers: String? = ""
    var mood: String? = ""
    var progress: String? = ""
}
