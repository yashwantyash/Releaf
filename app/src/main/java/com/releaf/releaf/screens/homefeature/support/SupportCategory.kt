package com.releaf.releaf.screens.homefeature.support

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.R
import com.releaf.releaf.theme.ReLeafTheme

@Composable
fun SupportTab(
    navController: NavHostController
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Self Support", "External Support")
    val selfTabCardContent = listOf(
        "Breathing Techniques" to R.drawable.breathing,
        "Yoga" to R.drawable.yoga,
        "Guided Meditation" to R.drawable.meditation,
        "Cognitive Behavioral Technique" to R.drawable.cognitive
    )
    val externalTabCardContent = listOf(
        "Find a Therapist" to R.drawable.therapist,
        "Talk to someone now" to R.drawable.talk,
        "SAMHSA" to R.drawable.samsha,
        "Support group for SUD" to R.drawable.group,
        "American Addiction Center" to R.drawable.american_png,
        "Smart Recovery" to R.drawable.recovery

    )

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .height(120.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(24.dp),
            contentAlignment = Alignment.TopStart

        ) {
            Column {
                Text(text = "Support", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title, fontWeight = FontWeight.Bold) }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> TabContent(selfTabCardContent) { cardId ->
                when (cardId) {
                    R.drawable.breathing -> { /* Handle breathing card click */
                    }

                    R.drawable.yoga -> { /* Handle yoga card click */
                    }

                    R.drawable.meditation -> { /* Handle meditation card click */
                    }

                    R.drawable.cognitive -> { /* Handle cognitive card click */
                    }
                }
            }

            1 -> TabContent(externalTabCardContent) { cardId ->
                when (cardId) {
                    R.drawable.therapist -> { /* Handle therapist card click */
                    }

                    R.drawable.talk -> { /* Handle talk card click */
                    }

                    R.drawable.samsha -> { /* Handle SAMHSA card click */
                    }

                    R.drawable.cognitive -> { /* Handle support group card click */
                    }
                }

            }
        }
    }
}

@Composable
fun TabContent(cardContent: List<Pair<String, Int>>, onCardClick: (Int) -> Unit) {
    val scrollState = rememberScrollState()

    cardContent.forEachIndexed { index, pair ->
        Card(
            modifier = Modifier
                .fillMaxWidth().verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { onCardClick(pair.second) },
            elevation = CardDefaults.cardElevation(4.dp)

        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(2.dp),
                    painter = painterResource(id = pair.second),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = pair.first,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SupportTabPreview() {
    ReLeafTheme {
        SupportTab(
            navController = rememberNavController()
        )
    }
}