package com.releaf.releaf.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.releaf.releaf.R
import com.releaf.releaf.ui.theme.ReLeafTheme
import com.releaf.releaf.utility.LogoAndName
import com.releaf.releaf.utility.MyInputField
import com.releaf.releaf.utility.MyNormalText
import com.releaf.releaf.utility.SigninBtn
import com.releaf.releaf.utility.UnderlinedText

@Composable
fun LoginScreen(
    navController: NavController,
    name: String = stringResource(id = R.string.app_name),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isCheckingUserExistence by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoAndName(name)
        Spacer(modifier = Modifier.height(32.dp))

        //email field
        MyInputField(
            label = R.string.email,
            leadIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email
        ) {
            email = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        MyInputField(
            label = R.string.password,
            leadIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password
        ) {
            password = it
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isCheckingUserExistence) {
            LinearProgressIndicator()
        } else {
            SigninBtn(
                R.string.login_button,
                navController,
                "home"
            )
            {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    isCheckingUserExistence = true

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "User Authenticated", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate("home")
                            } else {
//                                Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show()
                                task.exception?.let { exception ->
                                    // Handle other registration errors
                                    val errorMessage =
                                        exception.localizedMessage ?: "An error occurred"
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                                isCheckingUserExistence = false
                            }
                        }
                } else {
                    Toast.makeText(context, "Empty Email or Password", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            MyNormalText(valueId = R.string.ask_signup)
            Spacer(modifier = Modifier.width(8.dp))
            UnderlinedText(
                value = "Register Now!",
                navController = navController,
                desScreen = "signup"
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