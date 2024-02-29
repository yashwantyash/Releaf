package com.releaf.releaf.screens.homefeature

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.releaf.releaf.components.CheckBox
import com.releaf.releaf.components.OutFillBtn
import com.releaf.releaf.components.WavyTitle
import com.releaf.releaf.theme.ReLeafTheme

@Composable
fun AddJournalCheckbox(
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        WavyTitle(title = "Journal")
        BoxCheckbox(title = "Triggers", list = listOf<String>("Light", "Medium Controllable", "Extreme", "Extreme Controllable"))
        BoxCheckbox(title = "Mood", list = listOf<String>("Happy", "Neutral","Aggressive", "Mood Swings"))
        BoxCheckbox(title = "Progress", list = listOf<String>("Feeling better than yesterday ", "Feeling same as yesterday ", "Feeling worse", "I think I'm going back to square one "))
        Spacer(modifier = Modifier.height(36.dp))
        OutFillBtn(
            textOutl = "Back",
            outOnClick = {},
            fillOnClick = {},
            txtFill = "Next"
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
}
@Composable
fun BoxCheckbox(title: String, list: List<String>){
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        CheckBox(list)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddJournalCheckboxPreview() {
    ReLeafTheme {
        AddJournalCheckbox()
    }
}