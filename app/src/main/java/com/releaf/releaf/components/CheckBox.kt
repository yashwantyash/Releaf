package com.releaf.releaf.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CheckBox(
    challenges: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    val checkBoxStates = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }

    challenges.forEach { option ->
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
//                    checked = checkBoxStates.getValue(item),
                checked = option == selectedOption,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        onOptionSelected(option)
                    }
                },
//                    modifier = Modifier.padding(end = 5.dp)
            )
            Text(text = option)
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable()
fun CheckBoxPreview(

) {
//    CheckBox(challenges = listOf("Sadique"))

}