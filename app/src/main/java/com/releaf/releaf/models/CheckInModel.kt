package com.releaf.releaf.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.releaf.releaf.utility.Constants.CHECKIN_TABLE

@Entity(tableName = CHECKIN_TABLE)
data class CheckInModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeOfDay: String = "nu",
    val checkInDate: String= "nu",
    val checkInCount: Int,
    val selectedEmotion: String= "nu",
    val intention: String= "nu",
    val eveningSliderValue: Int? = null, // Null for morning
)
