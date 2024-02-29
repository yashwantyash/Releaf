package com.releaf.releaf.screens.homefeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.R
import com.releaf.releaf.components.OutFillBtn
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.components.WavyTitle

@Composable
fun CheckIn(
    navController: NavHostController = rememberNavController()
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WavyTitle(title = "Morning Checkin")
        Text(
            text = "It's a new day! How do you feel?",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
//                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(100.dp)
                .graphicsLayer {
                    shape = RoundedCornerShape(12.dp)
                    clip = true
                    shadowElevation = 12f
                }
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Row(
//                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {

                Spacer(modifier = Modifier.weight(1f))
                Emoji(subtitle = "Happy", imageId = R.drawable.happy)
                Spacer(modifier = Modifier.weight(1f))
                Emoji(subtitle = "Hopeful", imageId = R.drawable.hopeful)
                Spacer(modifier = Modifier.weight(1f))
                Emoji(subtitle = "Neutral", imageId = R.drawable.neutral)
                Spacer(modifier = Modifier.weight(1f))
                Emoji(subtitle = "unsure", imageId = R.drawable.unsure)
                Spacer(modifier = Modifier.weight(1f))
                Emoji(subtitle = "Sad", imageId = R.drawable.sad)
                Spacer(modifier = Modifier.weight(1f))
                Emoji(subtitle = "Worst", imageId = R.drawable.heart)
                Spacer(modifier = Modifier.weight(1f))

            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Set an Intention for your day",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        Spacer(modifier = Modifier.height(16.dp))

        var textValue by remember { mutableStateOf("") }

        OutlinedTextField(
            value = textValue,
            onValueChange = {
                textValue = it
//                onValueChange(it)
            },
            textStyle = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(200.dp),
            shape = RoundedCornerShape(40f),

            label = {
                Text(
                    text = "My intention for today is",
                    fontSize = 18.sp
                )
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "\"Don't Let yesterday take up too much of today\"",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,

//             textDecoration = TextDecoration.Underline

        )
        Spacer(modifier = Modifier.height(32.dp))
        Spacer(modifier = Modifier.weight(1f))

            OutFillBtn(
                textOutl = "Back",
                outOnClick = {},
                fillOnClick = {},
                txtFill = "Save"
            )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun Emoji(
    subtitle: String,
    imageId: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(imageId),
            contentDescription = subtitle
        )
        Text(text = subtitle, fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
    }
}
//
//@Composable
//fun MyButton(
//    text: String,
//    onClick: () -> Unit = {},
//    contentColor: Color = MaterialTheme.colorScheme.primary,
//) {
//
//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckInScreenPreview() {
    ReLeafTheme {
        CheckIn(navController = rememberNavController())
    }
}