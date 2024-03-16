package com.releaf.releaf.screens.homefeature

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.R
import com.releaf.releaf.components.OutFillBtn
import com.releaf.releaf.components.WavyTitle
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.CheckInModel
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.Constants.HOME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

//@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DailyCheckIn(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) {

    val scrollState = rememberScrollState()
    var selectedEmojiIndex by remember { mutableStateOf(-1) }
    var  emojiError by remember { mutableStateOf(false)}
    var selectedEmotion by remember { mutableStateOf("") }
    var temporaryMessage by remember { mutableStateOf("") }
    var showTemporaryMassage by remember { mutableStateOf(false) }
    var showHotlineDialog by remember { mutableStateOf(false) }
    var intention by remember { mutableStateOf("") }
    val currentTimeHour = LocalTime.now().hour
    var checkInCount by remember { mutableStateOf(1) }
    var sliderValue by remember { mutableStateOf(0f) }
    var sliderValText by remember { mutableStateOf(false) }
    val greetingText: String
    val timeOfDay: String
    var showError by remember { mutableStateOf(false) }


    val (greeting, intentionPrompt) = when {
        currentTimeHour < 12 -> "It's a new day, How do you feel?" to "Set an intention for your day:"
        currentTimeHour < 18 -> "How do you feel so far?" to "How are you doing with your intention of the day?"
        else -> "Are you ready for tomorrow" to "Have you met your intention?"
    }

    when {
        currentTimeHour < 12 -> {
            greetingText = "Morning Check-In"
            timeOfDay = "Morning"
        }

        currentTimeHour < 18 -> {
            greetingText = "Afternoon Check-In"
            timeOfDay = "Afternoon"
        }

        else -> {
            greetingText = "Night Check-In"
            timeOfDay = "Night"
        }


    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WavyTitle(title = "Morning Checkin")
        Text(
            text = "It's a new day! How do you feel?",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
//                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(100.dp)
                .graphicsLayer {
                    shape = RoundedCornerShape(12.dp)
                    clip = true
                    shadowElevation = 12f
                }
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Row(
//                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {

                Spacer(modifier = Modifier.weight(1f))
                Emoji(
                    subtitle = "Happy",
                    imageId = R.drawable.happy,
                    isSelected = selectedEmojiIndex == 0,
                ) {
                    emojiError = false
                    selectedEmojiIndex = 0
                    selectedEmotion = "Happy"
                    temporaryMessage = "Glad you are happy!"
                    showTemporaryMassage = true
                }

                Spacer(modifier = Modifier.weight(1f))

                Emoji(
                    subtitle = "Hopeful", imageId = R.drawable.hopeful,
                    isSelected = selectedEmojiIndex == 1,
                ) {
                    emojiError = false
                    selectedEmojiIndex = 1
                    selectedEmotion = "Hopeful"
                    temporaryMessage = "Never lose hope! Keep going"
                    showTemporaryMassage = true
                }

                Spacer(modifier = Modifier.weight(1f))

                Emoji(
                    subtitle = "Neutral", imageId = R.drawable.neutral,
                    isSelected = selectedEmojiIndex == 2,
                ) {
                    emojiError = false
                    selectedEmojiIndex = 2
                    selectedEmotion = "Neutral"
                    temporaryMessage = "Hang in there. You are doing great!"
                    showTemporaryMassage = true
                }

                Spacer(modifier = Modifier.weight(1f))

                Emoji(
                    subtitle = "Unsure", imageId = R.drawable.unsure,
                    isSelected = selectedEmojiIndex == 3,
                ) {
                    emojiError = false
                    selectedEmojiIndex = 3
                    selectedEmotion = "Unsure"
                    temporaryMessage = "It's okay to be unsure. Click on Support to feel better"
                    showTemporaryMassage = true
                }
                Spacer(modifier = Modifier.weight(1f))
                Emoji(
                    subtitle = "Sad",
                    imageId = R.drawable.sad,
                    isSelected = selectedEmojiIndex == 4,
                ) {
                   emojiError = false
                    selectedEmojiIndex = 4
                    selectedEmotion = "Sad"
                    temporaryMessage = "It's ok to be not ok. Help is available"
                    showTemporaryMassage = true
                }
                Spacer(modifier = Modifier.weight(1f))
                Emoji(
                    subtitle = "Worst",
                    imageId = R.drawable.heart,
                    isSelected = selectedEmojiIndex == 5,
                ) {
                    emojiError = false
                    selectedEmojiIndex = 5
                    selectedEmotion = "Worst"
                    showHotlineDialog = true
                }
                Spacer(modifier = Modifier.weight(1f))


                if (showHotlineDialog) {
                    AlertDialog(
                        onDismissRequest = { showHotlineDialog = false },
                        title = {
                            Text(text = "Want to talk to someone?")
                        },
                        text = {
                            Text(text = "Connect me to the hotline")
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    showHotlineDialog = false
                                }
                            ) {
                                Text(text = "Connect")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showHotlineDialog = false
                                }
                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    )
                }

            }
        }
        if (emojiError) {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, start = 24.dp)
                    .align(Alignment.Start),
                text = "Please select your mood",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = intentionPrompt,
            textAlign = TextAlign.Start,
            style = TextStyle(
//                lineHeight = 36.sp
            ),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = intention,
            onValueChange = {
                intention = it
                showError = false
//                onValueChange(it)
            },
            textStyle = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(200.dp),
            shape = RoundedCornerShape(40f),

            label = {
                Text(
                    text = "My intention for today is",
                    fontSize = 18.sp
                )
            },
            isError = showError,
            singleLine = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
        )
        if (showError) {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, start = 30.dp)
                    .align(Alignment.Start),
                text = "Field cannot be empty",
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "\"Don't Let yesterday take up too much of today\"",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,

//             textDecoration = TextDecoration.Underline

        )
        Spacer(modifier = Modifier.height(24.dp))
        if (currentTimeHour > 18) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Slider(
                    modifier = Modifier.weight(1f),
                    value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                        sliderValText = true
                    },
                    valueRange = 0f..100f,
                    steps = 100,
                    onValueChangeFinished = {
                        // You can use this event to trigger any action once the user has stopped moving the slider
                        sliderValText = false
                    },
                )
                if (sliderValText) {
                    Text(
//                        overflow = TextOverflow.Clip,
                        text = sliderValue.toInt().toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.weight(1f))

        OutFillBtn(
            textOutl = "Back",
            outOnClick = {
//                TODO("need to click on back button twice")
                navController.popBackStack()
//                navController.navigate(HOME)
            },
            fillOnClick = {
                if (selectedEmojiIndex == -1) {
                    emojiError = true
                } else if (intention.isEmpty()) {
                    showError = true
                } else {
                    saveCheckIn(
                        context,
                        timeOfDay,
                        LocalDate.now(),
                        checkInCount,
                        selectedEmotion,
                        intention,
                        if (currentTimeHour >= 18) sliderValue.toInt() else null
                    )

                        Toast.makeText(context, "Check-in saved", Toast.LENGTH_SHORT)
                            .show()

//                    TODO("need to click on back button twice")
                    navController.popBackStack()
//                    navController.navigate(HOME)
                }
            },
            txtFill = "Save"
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (showTemporaryMassage) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .graphicsLayer {
                        shape = RoundedCornerShape(6.dp)
                        clip = true
                    }
                    .background(MaterialTheme.colorScheme.secondaryContainer)
//                    .padding(16.dp)

            ) {
                Text(
                    text = temporaryMessage,
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 12.dp)
                )
            }

            LaunchedEffect(key1 = temporaryMessage) {
                delay(1500)
                showTemporaryMassage = false
            }
        }

    }
}


@Composable
fun Emoji(
    subtitle: String,
    imageId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {

//    var isSelected by remember { mutableStateOf(false) }
    val tintColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick()
//            isSelected = !isSelected // toggling selection
        }
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(imageId),
            colorFilter = ColorFilter.tint(tintColor),  // Apply color filter based on selection
            contentDescription = subtitle
        )
        Text(
            text = subtitle, fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun saveCheckIn(
    context: Context,
    timeOfDay: String,
    checkInDate: LocalDate = LocalDate.now(),
    checkInCount: Int,
    selectedEmotion: String,
    intention: String,
    eveningSliderValue: Int?
) {

    var insertionSuccessfull = false

    GlobalScope.launch(Dispatchers.IO) {
        val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val formattedDate = checkInDate.format(dateFormatter)

        val newCheckIn = CheckInModel(
            timeOfDay = timeOfDay,
            checkInDate = formattedDate,
            checkInCount = checkInCount,
            selectedEmotion = selectedEmotion,
            intention = intention,
            eveningSliderValue = eveningSliderValue
        )
        val dao = ReleafDatabase.getDatabase(context).dailyCheckInDao()
        dao.insertCheckIn(newCheckIn)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckInScreenPreview() {
    ReLeafTheme {
        DailyCheckIn(navController = rememberNavController())
    }
}