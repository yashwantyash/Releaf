package com.releaf.releaf.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

@Composable
fun SupportTabRow(
    cardContent: List<Pair<String, Int>>,
//    context: Context,
    onCardClick: (Int) -> Unit
) {

    val scrollState = rememberScrollState()

    cardContent.forEachIndexed { index, pair ->
        Card(
            modifier = Modifier
                .fillMaxWidth().verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onCardClick(pair.second)
                },
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
