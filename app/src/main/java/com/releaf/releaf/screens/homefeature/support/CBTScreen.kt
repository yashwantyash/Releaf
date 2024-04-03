package com.releaf.releaf.screens.homefeature.support

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.components.WavyTitle

@Composable
fun CBTScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var sourceText by remember { mutableStateOf("") }
    var beliefText by remember { mutableStateOf("") }
    var balancedThoughtsText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    var sourceError by remember { mutableStateOf(false) }
    var beliefError by remember { mutableStateOf(false) }
    var balancedThoughtsError by remember { mutableStateOf(false) }
    var resultError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
    ) {
        WavyTitle(title = "CBT For Anxiety")
        Text(
            text = "Follow this step by step interactive technique to deal with your anxiety",
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            isError = sourceError,
            value = sourceText,
            onValueChange = {
                sourceText = it
                sourceError = it.isEmpty()
            },
            label = { Text(text = "Source of Anxiety") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(120.dp)
        )
        OutlinedTextField(
            isError = beliefError,
            value = beliefText,
            onValueChange = {
                beliefText = it
                beliefError = it.isEmpty()
            },
            label = { Text(text = "Negative beliefs you have about yourself") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(120.dp)
        )
        OutlinedTextField(
            isError = balancedThoughtsError,
            value = balancedThoughtsText,
            onValueChange = {
                balancedThoughtsText = it
                balancedThoughtsError = it.isEmpty()
            },
            label = { Text(text = "Develop balanced thoughts") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(120.dp)
        )
        OutlinedTextField(
            isError = resultError,
            value = resultText,
            onValueChange = {
                resultText = it
                resultError = it.isEmpty()
            },
            label = { Text(text = "Result of situation") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(120.dp)
        )

        // Save Button
        Button(
            modifier = Modifier
                .padding(bottom = 36.dp)
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            onClick = {
                sourceError = sourceText.isEmpty()
                beliefError = beliefText.isEmpty()
                balancedThoughtsError = balancedThoughtsText.isEmpty()
                resultError = resultText.isEmpty()

                // Handle saving if all fields are filled
                if (!sourceError && !beliefError && !balancedThoughtsError && !resultError) {
                    // Save logic here
                    Toast.makeText(context, "saving....", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "Save"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Prev() {
    CBTScreen(navController = rememberNavController())
}
