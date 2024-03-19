package com.releaf.releaf.screens.homefeature.journal

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.components.WavyTitle
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.journal.CheckBoxViewModel
import com.releaf.releaf.models.journal.Journal
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.Constants.HOME
import com.releaf.releaf.utility.Constants.JOURNAL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteJournal(
    navController: NavController,
    checkBoxViewModel: CheckBoxViewModel,
    context: Context = LocalContext.current
) {
    val scrollState = rememberScrollState()

    val options = listOf("Amazing", "Good", "Just Fine", "Meh", "Bad", "Very Bad", "Rough")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("How was your day?") }

    var journalTitle by remember { mutableStateOf("") }
    var journalDescription by remember { mutableStateOf("") }

    val checkInDate: LocalDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = checkInDate.format(dateFormatter)

    var moodError by remember { mutableStateOf(false) }
    var titleError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WavyTitle(title = "Journal")

        Text(
            text = "Journal your thoughts",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp),
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            OutlinedTextField(
                isError = moodError,
                value = selectedOptionText,
                onValueChange = {
                    moodError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                label = { Text(text = "Mood") },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
            )


            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach {

                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            selectedOptionText = it
                            expanded = false
                            moodError = false
                        },
//                        modifier = Modifier
//                            .fillMaxWidth(),
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }

            }
        }
        if (moodError) {
            Text(
                modifier = Modifier
                    .padding(top = 1.dp,start = 20.dp)
                    .align(Alignment.Start),
                text = "Please select your mood",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            isError = titleError,
            value = journalTitle,
            onValueChange = {
                journalTitle = it
                titleError = false
            },
            label = { Text("Title your day") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        if (moodError) {
            Text(
                modifier = Modifier
                    .padding(top = 1.dp,start = 20.dp)
                    .align(Alignment.Start),
                text = "Plese enter title of your day",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            isError = descriptionError,
            value = journalDescription,
            onValueChange = {
                journalDescription = it
                descriptionError = false
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            label = { Text("Describe how your day was") },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(150.dp)
        )
        if (moodError) {
            Text(
                modifier = Modifier
                    .padding(top = 1.dp,start = 20.dp)
                    .align(Alignment.Start),
                text = "Please describe your day",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(54.dp))
        Button(
            onClick = {
                moodError = selectedOptionText=="How was your day?"
                titleError = journalTitle.isEmpty()
                descriptionError = journalDescription.isEmpty()

                if (!moodError && !titleError && !descriptionError) {

                    val newJournal = Journal(
                        date = formattedDate,
                        triggers = checkBoxViewModel.triggers,
                        mood = checkBoxViewModel.mood,
                        progress = checkBoxViewModel.progress,
                        aboutDay = selectedOptionText,
                        title = journalTitle,
                        description = journalDescription
                    )

                    GlobalScope.launch(Dispatchers.IO) {
                        val dao = ReleafDatabase.getDatabase(context).journalDao()
                        dao.insertJournal(newJournal)
                    }
                    navController.popBackStack(route = HOME, inclusive = true)
                    navController.navigate(JOURNAL)
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Save")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WriteJournalPreview() {
    ReLeafTheme {
        val navController = rememberNavController()
        val checkBoxViewModel = remember { CheckBoxViewModel() }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            WriteJournal(navController = navController, checkBoxViewModel = checkBoxViewModel)
        }
    }
}