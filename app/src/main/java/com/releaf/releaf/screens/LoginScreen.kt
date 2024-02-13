package com.releaf.releaf.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.R
import com.releaf.releaf.composables.LogoAndName
import com.releaf.releaf.composables.MyInputField
import com.releaf.releaf.composables.MyNormalText
import com.releaf.releaf.composables.SignupBtn
import com.releaf.releaf.composables.UnderlinedText
import com.releaf.releaf.ui.theme.ReLeafTheme

@Composable
fun LoginScreen(
    navController: NavController,
    name: String = stringResource(id = R.string.app_name),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoAndName(name)
        Spacer(modifier = Modifier.height(32.dp))
        MyInputField(
            label = R.string.outline_text1,
            leadIcon = Icons.Default.Person,
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyInputField(
            label = R.string.outline_text2,
            leadIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.height(24.dp))
        SignupBtn(R.string.login_button, navController, "signup")
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            MyNormalText(valueId = R.string.ask_signup)
            Spacer(modifier = Modifier.width(8.dp))
            UnderlinedText(
                value = "Register Now!",
                navController = navController,
                desScreen = "home"
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    ReLeafTheme {
        LoginScreen(navController)
    }
}