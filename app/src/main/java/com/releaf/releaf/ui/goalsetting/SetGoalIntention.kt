package com.example.releaftestapp.ui.goalsetting

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.releaftestapp.ui.goalsetting.model.ChallengeEvent
import com.example.releaftestapp.ui.goalsetting.model.ChallengeState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetGoalIntention(
    state: ChallengeState,
    onEvent: (ChallengeEvent) -> Unit,
    navController: NavHostController
) {
    var challengeSpan by remember { mutableIntStateOf(0) }
    val soberStartDate = state.soberStartDate

    val calenderUnit = arrayOf("days", "months", "years")
    val selectedCalenderUnit = remember { mutableStateOf(calenderUnit[0]) }
    val expandedCalenderUnitMenu = remember { mutableStateOf(false) }

    var challengeDescription by remember { mutableStateOf(state.challengeDescription) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Set Your Goal and Intentions",
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "I want to stay sober for : ")

            TextField(
                value = challengeSpan.toString(),
                onValueChange = {
                    val newValue = it.toIntOrNull() ?: 0
                    challengeSpan = if (newValue < 0) 0 else newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f)
            )
            Spacer(modifier = Modifier.width(10.dp))

            //days, months, years dropdown code here
            ExposedDropdownMenuBox(
                expanded = expandedCalenderUnitMenu.value,
                onExpandedChange = {
                    expandedCalenderUnitMenu.value = !expandedCalenderUnitMenu.value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.25f)
            ) {
                TextField(
                    value = selectedCalenderUnit.value,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCalenderUnitMenu.value)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedCalenderUnitMenu.value,
                    onDismissRequest = { expandedCalenderUnitMenu.value = false }
                ) {
                    calenderUnit.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = {
                                selectedCalenderUnit.value = it
                                expandedCalenderUnitMenu.value = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = challengeDescription,
            onValueChange = {
                challengeDescription = it
                onEvent(ChallengeEvent.SetChallengeDescription(challengeDescription))
            },
            label = { Text("I want to stay sober because...") },
            maxLines = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val soberEndDate = calculateSoberEndDate(
                    soberStartDate,
                    challengeSpan,
                    selectedCalenderUnit.value
                )
                onEvent(ChallengeEvent.SetSoberEndDate(soberEndDate))
                onEvent(ChallengeEvent.SaveChallenge)
                navController.navigate("HomeScreen")
            },
            enabled = challengeDescription != "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Save")
        }
    }
}

fun calculateSoberEndDate(startDate: LocalDate, span: Int, unit: String): LocalDate {
    var endDate = startDate
    when (unit) {
        "days" -> endDate = startDate.plusDays(span.toLong())
        "months" -> endDate = startDate.plusMonths(span.toLong())
        "years" -> endDate = startDate.plusYears(span.toLong())
    }
    return endDate
}
