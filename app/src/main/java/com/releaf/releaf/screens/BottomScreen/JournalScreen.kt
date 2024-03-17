package com.releaf.releaf.screens.BottomScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.releaf.releaf.components.WavyTitle
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.journal.Journal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun JournalScreen(
    navController: NavController,
    context: Context = LocalContext.current
//    journalViewModel: JournalViewModel = viewModel()
) {
//    val journals = remember { journalViewModel.allJournals }
////    val journals by journalViewModel.allJournals.observeAsState(initial = emptyList())
////    val journals: List<Journal> by journalViewModel.allJournals.asFlow().collectAsState(initial = emptyList())
//
//    LaunchedEffect(key1 = true) {
//        journalViewModel.getAllJournalByDate()
//    }
    var db by remember { mutableStateOf<List<Journal>>(emptyList()) }

    LaunchedEffect(Unit) {
        val journals = withContext(Dispatchers.IO) {
            ReleafDatabase.getDatabase(context).journalDao().getAllJournalByDate()
        }
        db = journals
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
        /* .verticalScroll(scrollState)*/,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WavyTitle(title = "Your Journal")

        Row {
            EachRow("Date", modifier = Modifier.weight(0.5f), fw = FontWeight.Bold, fs = 24.sp)
            EachRow(
                "Journal Title",
                modifier = Modifier.weight(0.5f),
                fw = FontWeight.Bold,
                fs = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.padding(bottom = 150.dp)
        ) {
            items(db.size) { index ->
                val journal = db[index]
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    EachRow(journal.date.toString(), modifier = Modifier.weight(0.5f))
                    EachRow(journal.title.toString(), modifier = Modifier.weight(0.5f))
                }
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

//@Composable
//@Preview
//fun JournalScreenPreview() {
//    ReLeafTheme {
//        JournalScreen()
//    }
//}