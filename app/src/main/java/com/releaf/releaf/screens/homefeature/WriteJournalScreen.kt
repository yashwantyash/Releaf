package com.releaf.releaf.screens.homefeature

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.releaf.releaf.components.WavyTitle
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.CheckBoxViewModel
import com.releaf.releaf.models.JournalModel
import com.releaf.releaf.utility.Constants.HOME
import com.releaf.releaf.utility.Constants.MAIN_ROUTE
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
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    val formattedDate = checkInDate.format(dateFormatter)

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
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        Spacer(modifier = Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            OutlinedTextField(
                value = selectedOptionText,
                onValueChange = {},
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
                        },
//                        modifier = Modifier
//                            .fillMaxWidth(),
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = journalTitle,
            onValueChange = { journalTitle = it },
            label = { Text("Title your day") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = journalDescription,
            onValueChange = { journalDescription = it },
            label = { Text("Describe how your day was") },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {

                val newJournal = JournalModel(
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
//                    navController.navigate("HomeScreen")
                navController.popBackStack(route = HOME, inclusive = false)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Save")
        }
    }

}