package com.releaf.releaf.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutFillBtn(
    textOutl: String,
    outOnClick: () -> Unit = {},
    fillOnClick: () -> Unit = {},
    txtFill: String

) {
    Row(
        Modifier.padding(horizontal = 32.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(
            onClick = outOnClick,
            shape = RoundedCornerShape(10.dp)

        ) {
            Text(
                text = textOutl,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ),

            onClick = fillOnClick,
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                text = txtFill,
                fontSize = 18.sp,
            )

        }
    }
}