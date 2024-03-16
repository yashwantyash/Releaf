package com.releaf.releaf.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.releaf.releaf.theme.ReLeafTheme

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
        OutlinedBtn(textOutl, outOnClick)
//        OutlinedButton(
//            onClick = outOnClick,
//            shape = RoundedCornerShape(10.dp)
//
//        ) {
//            Text(
//                text = textOutl,
//                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
//                fontSize = 18.sp
//            )
//        }
        Spacer(modifier = Modifier.weight(1f))

        FilledBtn(fillOnClick, txtFill)
//        Button(
//            modifier = Modifier
//                .padding(horizontal = 16.dp)
//                .clip(
//                    RoundedCornerShape(10.dp)
//                ),
//
//            onClick = fillOnClick,
//            shape = RoundedCornerShape(10.dp)
//        ) {
//            Text(
//                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
//                text = txtFill,
//                fontSize = 18.sp,
//            )
//
//        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OutFillBtnPreview() {
    ReLeafTheme {


        Column {
            OutFillBtn(
                textOutl = "Outlined Button",
                outOnClick = { /* Handle outlined button click */ },
                fillOnClick = { /* Handle filled button click */ },
                txtFill = "Filled Button"
            )
        }
    }
}

