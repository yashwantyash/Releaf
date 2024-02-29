package com.releaf.releaf.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.releaf.releaf.R


@Composable
fun WavyTitle(
    title: String
) {

    Box(
        contentAlignment = Alignment.TopStart
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {

            }
            Image(
                painter = painterResource(id = R.drawable.releaf_wave),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .rotate(180f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer)
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp),
            text = title,
            fontSize = 40.sp,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }

}


