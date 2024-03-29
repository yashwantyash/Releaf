package com.releaf.releaf.screens.homefeature.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.components.CheckBox
import com.releaf.releaf.components.OutFillBtn
import com.releaf.releaf.components.WavyTitle
import com.releaf.releaf.models.journal.CheckBoxViewModel
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.Constants.WRITE_JOURNAL

@Composable
fun JournalCheckbox(
    navController: NavHostController,
//    context: Context = LocalContext.current,
    checkBoxViewModel: CheckBoxViewModel
) {
    val scrollState = rememberScrollState()
    var selectedTriggers by remember { mutableStateOf<String?>(null) }
    var selectedMood by remember { mutableStateOf<String?>(null) }
    var selectedProgress by remember { mutableStateOf<String?>(null) }

    var triggersError by remember { mutableStateOf(false) }
    var moodError by remember { mutableStateOf(false) }
    var progressError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(scrollState)
    ) {
        WavyTitle(title = "Journal")

        BoxCheckbox(
            title = "Triggers",
            optionsList = listOf<String>(
                "Light",
                "Medium Controllable",
                "Extreme",
                "Extreme Controllable"
            ),
            selectedOption = selectedTriggers,
            onOptionSelected = {
                selectedTriggers = it
                triggersError = false
            }
        )

        if (triggersError) {
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp, start = 24.dp)
                    .align(Alignment.Start),
                text = "Please select a trigger",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )
        }

        BoxCheckbox(
            title = "Mood",
            optionsList = listOf<String>("Happy", "Neutral", "Aggressive", "Mood Swings"),
            selectedOption = selectedMood,
            onOptionSelected = {
                selectedMood = it
                moodError = false
            }
        )

        if (moodError) {
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp, start = 24.dp)
                    .align(Alignment.Start),
                text = "Please select a mood",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )
        }

        BoxCheckbox(
            title = "Progress",
            optionsList = listOf<String>(
                "Feeling better than yesterday ",
                "Feeling same as yesterday ",
                "Feeling worse",
                "I think I'm going back to square one "
            ),
            selectedOption = selectedProgress,
            onOptionSelected = {
                selectedProgress = it
                progressError = false
            }
        )

        if (progressError) {
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp, start = 24.dp)
                    .align(Alignment.Start),
                text = "Please select a progress",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        OutFillBtn(
            textOutl = "Back",
            outOnClick = {
                navController.popBackStack()
            },
            fillOnClick = {
                triggersError = selectedTriggers == null
                moodError = selectedMood == null
                progressError = selectedProgress == null

                if (!triggersError && !moodError && !progressError) {
                    checkBoxViewModel.mood = selectedMood
                    checkBoxViewModel.triggers = selectedTriggers
                    checkBoxViewModel.progress = selectedProgress

                    navController.navigate(WRITE_JOURNAL)
                }
            },
            txtFill = "Next"
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun BoxCheckbox(
    title: String,
    optionsList: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 18.dp, top = 6.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 6.dp)
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        CheckBox(
            optionsList,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddJournalCheckboxPreview() {
    ReLeafTheme {
//        JournalCheckbox()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JournalCheckboxPreview() {
    ReLeafTheme {
        val navController = rememberNavController()
        val checkBoxViewModel = remember { CheckBoxViewModel() }

        JournalCheckbox(navController = navController, checkBoxViewModel = checkBoxViewModel)
    }
}
