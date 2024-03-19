package com.releaf.releaf.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.releaf.releaf.theme.ReLeafTheme

@Composable
fun EachJournal(
    trigger: String,
    mood: String,
    progress: String,
    date: String,
    title: String,
    aboutDay: String,
    description: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 22.sp,
             modifier = Modifier.padding(bottom = 4.dp, top = 10.dp))
        Text(text = "Trigger: $trigger, Mood: $mood")
        Text(text = "Progress: $progress")
        Text(text = "My day was ${aboutDay}.")
        Text(text = description, fontSize = 18.sp)
        Text(text = date, textAlign = TextAlign.End,
             modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        Divider()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EachJournalPreview() {
    // You can call your EachJournal composable function with sample data here
    ReLeafTheme {
        EachJournal(
            trigger = "Sample Trigger",
            mood = "Happy",
            progress = "Making progress",
            date = "2024-03-19",
            title = "Sample Title",
            aboutDay = "About the day...",
            description = "Sample description..."
        )
    }
}