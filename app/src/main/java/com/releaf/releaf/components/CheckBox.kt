package com.releaf.releaf.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CheckBox(
    challenges: List<String>
) {
    val checkBoxStates = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }

        challenges.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkBoxStates.getValue(item),
                    onCheckedChange = { checkBoxStates[item] = it },
//                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = item)
            }

        }


}


@Preview(showSystemUi = true, showBackground = true)
@Composable()
fun CheckBoxPreview(

) {
    CheckBox(challenges = listOf("Sadique"))

}