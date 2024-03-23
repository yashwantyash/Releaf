package com.example.releaftestapp.ui.goalsetting.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.releaftestapp.ui.goalsetting.model.ChallengeEvent
import com.example.releaftestapp.ui.goalsetting.model.ChallengeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    state: ChallengeState,
    onEvent: (ChallengeEvent) -> Unit,
    isSheetOpen: Boolean,
    navController: NavHostController,
    onCloseSheet: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    val category = arrayOf("Substance use challenges", "Mental Health challenges")
    val selectedCategory = remember { mutableStateOf(state.category) }
    val expandedCategoryMenu = remember { mutableStateOf(false) }

    val categoryTypes =
        when (selectedCategory.value) {
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


    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onCloseSheet() }
        ) {
            Text(
                text = "Seeking support for",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = expandedCategoryMenu.value,
                            onExpandedChange = {
                                expandedCategoryMenu.value = !expandedCategoryMenu.value
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                        ) {
                            TextField(
                                value = selectedCategory.value,
                                onValueChange = {
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategoryMenu.value)
                                },
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = expandedCategoryMenu.value,
                                onDismissRequest = { expandedCategoryMenu.value = false }
                            ) {
                                category.forEach {
                                    DropdownMenuItem(
                                        text = { Text(text = it) },
                                        onClick = {
                                            selectedCategory.value = it
                                            expandedCategoryMenu.value = false
                                        }
                                    )
                                }
                            }
                        }
                        categoryTypes.forEach { item ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = state.categoryType == item,
                                    onClick = {
                                        onEvent(ChallengeEvent.SetCategoryType(item))
                                    },
                                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                                )
                                Text(text = item)
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    onCloseSheet()
                                    expandedCategoryMenu.value = false
                                    onEvent(ChallengeEvent.SetCategoryType(""))
                                }, modifier = Modifier
                                    .weight(1f)
                            ) {
                                Text(text = "Cancel")
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                            Button(
                                onClick = {
                                    onEvent(ChallengeEvent.SetCategory(selectedCategory.value))
                                    navController.navigate("SoberCalender")
                                }, modifier = Modifier
                                    .weight(1f),
                                enabled = state.categoryType != ""
                            ) {
                                Text(text = "Continue")
                            }
                        }
                    }
                }
            }
        }
    }
}