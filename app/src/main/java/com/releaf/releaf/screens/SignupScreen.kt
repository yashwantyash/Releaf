package com.releaf.releaf.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.R
import com.releaf.releaf.composables.MyInputField
import com.releaf.releaf.composables.MyNormalText
import com.releaf.releaf.composables.SignupBtn
import com.releaf.releaf.composables.UnderlinedText
import com.releaf.releaf.theme.ReLeafTheme

/*
class SignupActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContent {
ReLeafTheme {
// A surface container using the 'background' color from the theme
Surface(
modifier = Modifier.fillMaxSize(),
color = MaterialTheme.colorScheme.background
) {
SignupScreen()
}
}
}
}
}
*/

@Composable
fun SignupScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.box),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
//                .scale(scaleX = 1f, scaleY = 2f)
            )


            Spacer(modifier = Modifier.height(1.dp))
            Image(
                painter = painterResource(id = R.drawable.releaf_wave),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .rotate(180f)
//                .scale(scaleX = 1f, scaleY = 2f)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = "ReLeaf",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp)
        ) {
            MyInputField(label = R.string.outline_text1, leadIcon = Icons.Default.Person)
            Spacer(modifier = Modifier.height(16.dp))
            MyInputField(label = R.string.outline_address, leadIcon = Icons.Default.Home)
            Spacer(modifier = Modifier.height(16.dp))
            MyInputField(
                label = R.string.password_txt,
                leadIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(16.dp))
            MyInputField(
                label = R.string.password_txt_confirm,
                leadIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(24.dp))
            SignupBtn(btnTxtId = R.string.btn_text_signup, navController, "home")
            Spacer(modifier = Modifier.height(18.dp))
            Row {
                MyNormalText(valueId = R.string.ask_signin)
                Spacer(modifier = Modifier.width(8.dp))
                UnderlinedText(value = "Log In", navController = navController, desScreen = "login")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    ReLeafTheme {
        SignupScreen(navController)
    }
}