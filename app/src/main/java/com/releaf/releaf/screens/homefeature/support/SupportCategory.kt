package com.releaf.releaf.screens.homefeature.support

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.R
import com.releaf.releaf.components.SupportTabRow
import com.releaf.releaf.theme.ReLeafTheme

@Composable
fun SupportTab(
    navController: NavHostController,

    ) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
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
        "SAMHSA" to R.drawable.guided,
        "Support group for SUD" to R.drawable.group,
        "American Addiction Center" to R.drawable.american_png,
        "Smart Recovery" to R.drawable.recovery

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.surface)
    ) {
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
                0 -> SupportTabRow(selfTabCardContent /*context*/) { cardId ->
                    when (cardId) {
                        R.drawable.breathing -> { /* Handle breathing card click */
                            openUrlInBrowser("", context)
                        }

                        R.drawable.yoga -> { /* Handle yoga card click */
                            openUrlInBrowser("", context)

                        }

                        R.drawable.meditation -> { /* Handle meditation card click */
                            openUrlInBrowser("", context)

                        }

                        R.drawable.cognitive -> { /* Handle cognitive card click */
                            openUrlInBrowser("", context)

                        }
                    }
                }

                1 -> SupportTabRow(externalTabCardContent) { cardId ->
                    when (cardId) {
                        R.drawable.therapist -> { /* Handle therapist card click */
                            openUrlInBrowser("https://www.betterhelp.com", context)
                        }

                        R.drawable.talk -> { /* Handle talk card click */
                            openUrlInBrowser(
                                "https://988lifeline.org/talk-to-someone-now/",
                                context
                            )

                        }

                        R.drawable.guided -> { /* Handle SAMHSA card click */
                            openUrlInBrowser(
                                "https://www.samhsa.gov/find-help/national-helpline",
                                context
                            )
                        }

                        R.drawable.cognitive -> { /* Handle support group card click */
                            openUrlInBrowser("https://www.nami.org/Home", context)

                        }

                        R.drawable.group -> { /* Handle SAMHSA card click */
                            openUrlInBrowser(
                                "https://www.addictioncenter.com/treatment/support-groups/",
                                context
                            )

                        }

                        R.drawable.american_png -> { /* Handle support group card click */
                            openUrlInBrowser(
                                "https://americanaddictioncenters.org/therapy-treatment/aftercare-support-groups",
                                context
                            )
                        }

                        R.drawable.recovery -> { /* Handle support group card click */
                            openUrlInBrowser("https://www.smartrecovery.org", context)

                        }
                    }
                }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            modifier = Modifier
                .padding(bottom = 36.dp)
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            onClick = {
                navController.popBackStack()
            },
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "Back"
            )

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

private fun openUrlInBrowser(url: String, context: Context) {
    if (url.isNotEmpty()) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "No link found...", Toast.LENGTH_SHORT).show()
    }
}