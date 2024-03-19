package com.releaf.releaf.screens.BottomScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.releaf.releaf.R
import com.releaf.releaf.components.EachJournal
import com.releaf.releaf.components.WavyTitle
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.journal.Journal
import com.releaf.releaf.utility.Constants.ADD_JOURNAL_CHECKBOX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun JournalScreen(
    navController: NavController,
    context: Context = LocalContext.current
) {

    var journalList by remember { mutableStateOf<List<Journal>>(emptyList()) }

    LaunchedEffect(Unit) {
        val journals = withContext(Dispatchers.IO) {
            ReleafDatabase.getDatabase(context).journalDao().getAllJournalByDate()
        }
        journalList = journals
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
        /* .verticalScroll(scrollState)*/,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WavyTitle(title = "Your Journal")
        if (journalList.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            LazyColumn(
                modifier = Modifier.padding(bottom = 150.dp)
            ) {
                items(journalList) { journal ->
//

                    EachJournal(
                        trigger = journal.triggers.toString(),
                        mood = journal.mood.toString(),
                        progress = journal.progress.toString(),
                        date = journal.date.toString(),
                        title = journal.title.toString(),
                        aboutDay = journal.aboutDay.toString(),
                        description = journal.description.toString()
                    )
                }
            }
        } else {

            Image(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 36.dp)
                    .graphicsLayer {
                        shape = RoundedCornerShape(12.dp)
                        clip = true
                        shadowElevation = 8f
                    },
                painter = painterResource(id = R.drawable.empty_img),
                contentDescription = null
            )
            Text(
                text = "No Journal Entry made yet!",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(80.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(ADD_JOURNAL_CHECKBOX)
                },
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text(text = "Add Journal", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun EachRow(
    text: String,
    modifier: Modifier,
    fw: FontWeight = FontWeight.Light,
    fs: TextUnit = 20.sp
) {
    Text(
        text = text,
        fontSize = fs,
        fontWeight = fw,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(vertical = 16.dp)
    )
}
