package com.example.releaftestapp.ui.goalsetting.model

import com.releaf.releaf.models.Challenge
import java.time.LocalDate

sealed interface ChallengeEvent {
    object SaveChallenge: ChallengeEvent
    data class DeleteChallenge(val challenge: Challenge): ChallengeEvent
    data class SetCategory(val category: String): ChallengeEvent
    data class SetCategoryType(val categoryType: String): ChallengeEvent
    data class SetSoberStartDate(val soberStartDate: LocalDate): ChallengeEvent
    data class SetSoberEndDate(val soberEndDate: LocalDate): ChallengeEvent
    data class SetChallengeDescription(val challengeDescription: String): ChallengeEvent
}