package com.releaf.releaf.models.journal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.releaf.releaf.utility.Constants.JOURNAL_TABLE
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = JOURNAL_TABLE)
data class Journal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String?= getCurrentDate(),
    val triggers: String? ,
    val mood: String?,
    val progress: String?,
    val aboutDay: String?,
    val title: String?,
    val description: String?
)
fun getCurrentDate():String{
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
//    return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/mm/yyyy"))
}
