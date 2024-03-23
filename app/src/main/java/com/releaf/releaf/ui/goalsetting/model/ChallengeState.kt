package com.example.releaftestapp.ui.goalsetting.model

import androidx.annotation.RequiresApi
import com.example.releaftestapp.data.local.model.Challenge
import java.time.LocalDate
import java.util.Date

data class ChallengeState(
    val challenges:List<Challenge> = emptyList(),
    val category: String = "Substance use challenges",
    val categoryType: String = "",
    val soberStartDate: LocalDate = LocalDate.now(),
    val soberEndDate: LocalDate = LocalDate.now(),
    val challengeDescription: String = ""
)
