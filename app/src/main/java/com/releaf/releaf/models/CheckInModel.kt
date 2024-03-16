package com.releaf.releaf.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.releaf.releaf.utility.Constants.CHECKIN_TABLE

@Entity(tableName = CHECKIN_TABLE)
data class CheckInModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeOfDay: String = "",
    val checkInDate: String= "",
    val checkInCount: Int,
    val selectedEmotion: String= "",
    val intention: String= "",
    val eveningSliderValue: Int? = null, // Null for morning
)
