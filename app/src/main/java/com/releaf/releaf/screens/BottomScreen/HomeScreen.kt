package com.releaf.releaf.screens.BottomScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.R
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.NavConst.CHECKIN

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(10.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Image(
                        modifier = Modifier.padding(4.dp),
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "logo"
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = stringResource(id = R.string.app_name),
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Image(
                painter = painterResource(id = R.drawable.releaf_wave),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .rotate(180f),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer)
            )
            Image(
                modifier = Modifier.graphicsLayer {
                    shape = RoundedCornerShape(12.dp)
                    clip = true
                    shadowElevation = 8f
                },
                painter = painterResource(id = R.drawable.stair),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Day 20 of Your Path",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(64.dp))

            HomeFeature(
                onClick = {
                          navController.navigate(CHECKIN)
                },
                title = "Daily CheckIn",
                subtitle = "Set an intention for your day"
            )
            Spacer(modifier = Modifier.height(32.dp))
            HomeFeature(
                onClick = {},
                title = "Write Journal",
                subtitle = "Login how your day was"
            )
            Spacer(modifier = Modifier.height(32.dp))
            HomeFeature(
                onClick = {},
                title = "Support",
                subtitle = "Checkout available resources"
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}



@Composable
fun HomeFeature(
    onClick: () -> Unit,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .clickable { onClick() }
            .padding(start = 32.dp, end = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Shifts items to the start and end
//        horizontalArrangement = Arrangement.Center // Center horizontally
    ) {
        Column(
            modifier = Modifier.clickable { onClick() }
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
//                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontSize = 24.sp,
            )
            Text(
                text = subtitle,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,

                )
        }
        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,

            modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable { onClick() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ReLeafTheme {
        HomeScreen(navController = rememberNavController())
    }
}