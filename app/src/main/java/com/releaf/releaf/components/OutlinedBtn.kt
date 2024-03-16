package com.releaf.releaf.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedBtn(
    textOutl: String,
    outOnClick: () -> Unit = {},
    modifier: Modifier = Modifier

) {
        OutlinedButton(
            onClick = outOnClick,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier

        ) {
            Text(
                text = textOutl,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                fontSize = 18.sp
            )
        }
}