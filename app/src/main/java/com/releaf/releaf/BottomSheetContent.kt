package com.releaf.releaf

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    isSheetOpen: Boolean,
    navController: NavHostController,
    onCloseSheet: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    val category = arrayOf("Substance use challenges", "Mental Health challenges")
    val selectedCategory = remember { mutableStateOf(category[0]) }
    val expanded = remember { mutableStateOf(false) }

    val challenges = when (selectedCategory.value) {
        "Substance use challenges" -> listOf(
            "Alcohol",
            "Cocaine",
            "Heroin",
            "Fentanyl",
            "Methamphetamine",
            "Cannabis (Marijuana/Pot/Weed)",
            "Hallucinogens",
            "Inhalants",
            "Ketamine",
            "Kratom",
            "LSD (Acid)",
            "MDMA (Ecstasy/Molly)",
            "Mescaline (Peyote)",
            "Methamphetamine (Crystal/Meth)",
            "PCP (Angel Dust)",
            "Psilocybin (Magic Mushrooms/Shrooms)",
            "Rohypnol® (Flunitrazepam/Roofies)",
            "Salvia",
            "Steroids (Anabolic)",
            "Synthetic Cannabinoids (K2/Spice)",
            "Synthetic Cathinones (Bath Salts/Flakka)",
            "Prescription Opioids (Oxy/Percs)",
            "Prescription Stimulants (Speed, Adderall)",
            "Prescription Benzodiazepines",
            "Prescription Gabapentin",
            "Over-the-Counter Medicines—Dextromethorphan (DXM)",
            "Over-the-Counter Medicines—Loperamide",
            "Nicotine/Tobacco (smoking, vaping, 'dipping')"
        )

        "Mental Health challenges" -> listOf(
            "Symptoms of Depression",
            "Symptoms of Anxiety",
            "Symptoms of ADHD inattentive type",
            "Symptoms of ADHD Impulsive type",
            "Symptoms of ADHD Combined type",
            "Symptoms of Bipolar I",
            "Symptoms of Bipolar II",
            "Symptoms of Psychosis",
            "Symptoms of Schizophrenia",
            "Symptoms of Schizoaffective",
            "Symptoms of Grief",
            "Symptoms of Obsessive-Compulsive disorder",
            "Symptoms pf Post traumatic stress disorder"
        )

        else -> emptyList()
    }
    val checkBoxStates = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onCloseSheet() }
        ) {
            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 60.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            ExposedDropdownMenuBox(
                                expanded = expanded.value,
                                modifier = Modifier.fillMaxWidth(),
                                onExpandedChange = {
                                    expanded.value = !expanded.value
                                }
                            ) {
                                TextField(
                                    value = selectedCategory.value,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                                    },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    modifier = Modifier.fillMaxWidth(),
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false },
                                ) {
                                    category.forEach {
                                        Box(modifier = Modifier.fillMaxWidth()) {
                                            DropdownMenuItem(
                                                text = { Text(text = it) },
                                                onClick = {
                                                    selectedCategory.value = it
                                                    expanded.value = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

// checkbox with challenge
                        challenges.forEach { item ->
                            Row(
//                                modifier = Modifier.padding(bottom = -4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = checkBoxStates.getValue(item),
                                    onCheckedChange = { checkBoxStates[item] = it },
                                    modifier = Modifier.padding(end = 5.dp)
                                )
                                Text(text = item)
                            }
                        }
// end buttons
                        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                            OutlinedButton(onClick = {
                                onCloseSheet()
                                expanded.value = false
                                selectedCategory.value = category[0]
                                checkBoxStates.clear()
                            }) {
                                Text(text = "Cancel")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Button(onClick = {
                                navController.navigate("SoberCalender")
                            }) {
                                Text(text = "Continue")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomPreview(

) {
    BottomSheetContent(isSheetOpen = true, navController = rememberNavController()) {

    }
}