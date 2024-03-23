package com.example.releaftestapp.ui.goalsetting

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.releaftestapp.ui.goalsetting.model.ChallengeEvent
import com.example.releaftestapp.ui.goalsetting.model.ChallengeState
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoberCalender(
    state: ChallengeState,
    onEvent: (ChallengeEvent) -> Unit,
    navController: NavHostController
) {

    val soberStartDateMillis =
        state.soberStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = soberStartDateMillis,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= soberStartDateMillis - TimeUnit.DAYS.toMillis(1)
            }
        })

            Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            )
            {
                Text(
                    text = "Set your Sober Start Date",
                    modifier = Modifier.padding(20.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                DatePicker(
                    state = datePickerState,
                )
                Spacer(
                    modifier = Modifier.height(
                        20.dp
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        navController.popBackStack()
                    }) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(15.dp))

                    Button(onClick = {
                        onEvent(ChallengeEvent.SetSoberStartDate(datePickerState.selectedDateMillis!!.toLocalDate()))
                        navController.navigate("SetGoalIntention")
                    }) {
                        Text(text = "Continue")
                    }
                }
            }
        }

        fun Long.toLocalDate(): LocalDate {
            val instant = Instant.ofEpochMilli(this)
            return instant.atZone(ZoneOffset.UTC).toLocalDate()
        }