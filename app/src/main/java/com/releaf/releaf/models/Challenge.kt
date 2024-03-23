package com.releaf.releaf.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity
data class Challenge(
    val categoryType: String,
    val soberStartDate: LocalDate,
    val soberEndDate: LocalDate,
    val challengeDescription: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    )
