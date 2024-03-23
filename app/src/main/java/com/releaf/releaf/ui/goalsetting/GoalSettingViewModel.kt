package com.example.releaftestapp.ui.goalsetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.releaftestapp.data.local.dao.ChallengeDao
import com.example.releaftestapp.data.local.model.Challenge
import com.example.releaftestapp.ui.goalsetting.model.ChallengeEvent
import com.example.releaftestapp.ui.goalsetting.model.ChallengeState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class GoalSettingViewModel(
    private val dao: ChallengeDao
) : ViewModel(){
    private val _state = MutableStateFlow(ChallengeState())
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _challenges = _state
        .flatMapLatest {
            dao.getChallengesOrderedByDate() // Or any other function based on your state
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state,_challenges){ state, challenges ->
        state.copy(
            challenges = challenges
        )
    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000), ChallengeState())

    fun onEvent(event: ChallengeEvent){
        when(event){
            is ChallengeEvent.DeleteChallenge -> {
                viewModelScope.launch {
                    dao.deleteChallenge(event.challenge)
                }
            }
            ChallengeEvent.SaveChallenge ->{
                val categoryType = state.value.categoryType
                val soberStartDate = state.value.soberStartDate
                val soberEndDate = state.value.soberEndDate
                val challengeDescription = state.value.challengeDescription

                if(categoryType.isBlank() || challengeDescription.isBlank()){
                    return
                }
                val challenge = Challenge(
                    categoryType = categoryType,
                    soberStartDate = soberStartDate,
                    soberEndDate = soberEndDate,
                    challengeDescription = challengeDescription
                )
                viewModelScope.launch {
                    dao.upsertChallenge(challenge)
                }
                _state.update { it.copy(
                    categoryType = "",
                    soberStartDate = LocalDate.now(),
                    soberEndDate = LocalDate.now(),
                    challengeDescription = ""
                ) }
            }
            is ChallengeEvent.SetCategory -> {
                _state.update { it.copy(
                    category = event.category
                ) }
            }
            is ChallengeEvent.SetCategoryType -> {
                _state.update { it.copy(
                    categoryType = event.categoryType
                ) }
            }
            is ChallengeEvent.SetSoberEndDate -> {
                _state.update { it.copy(
                    soberEndDate = event.soberEndDate
                ) }
            }
            is ChallengeEvent.SetChallengeDescription -> {
                _state.update { it.copy(
                    challengeDescription = event.challengeDescription
                ) }
            }
            is ChallengeEvent.SetSoberStartDate -> {
                _state.update { it.copy(
                    soberStartDate = event.soberStartDate
                ) }
            }
        }
    }

}