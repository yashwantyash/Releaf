package com.releaf.releaf.screens.AuthScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import com.releaf.releaf.R
import com.releaf.releaf.components.MyInputField
import com.releaf.releaf.components.MyNormalText
import com.releaf.releaf.components.SignupBtn
import com.releaf.releaf.components.UnderlinedText
import com.releaf.releaf.models.User
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.Constants
import com.releaf.releaf.utility.Constants.HOME
import com.releaf.releaf.utility.Constants.LOGIN
import com.releaf.releaf.utility.Constants.MAIN_ROUTE

@Composable
fun SignupScreen(rootNavController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var isCreatingAccount by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()
    val database = Firebase.database

    Box {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.box),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
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

            MyInputField(
                label = R.string.fullname,
                leadIcon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            ) {
                fullName = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            MyInputField(
                label = R.string.phone,
                leadIcon = Icons.Default.Phone,
                keyboardType = KeyboardType.Phone
            ) {
                phone = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            MyInputField(
                label = R.string.email,
                leadIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Text
            ) {
                email = it
            }

            Spacer(modifier = Modifier.height(16.dp))
            MyInputField(
                label = R.string.password_txt,
                leadIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            ) {
                password = it
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (isCreatingAccount) {
                LinearProgressIndicator()
            } else {
                SignupBtn(
                    btnTxtId = R.string.btn_text_signup, rootNavController, "home"
                ) {
                    if (fullName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                        isCreatingAccount = true
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = auth.currentUser
                                    val userId = user?.uid ?: ""
                                    val userRef = database.reference.child("user").child(userId)
                                    userRef.setValue(User(fullName = fullName, phone = phone, email = email))
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                context,
                                                "Registration Successful",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            rootNavController.popBackStack()
                                            rootNavController.navigate(MAIN_ROUTE)
                                        }
                                        .addOnFailureListener { exception ->
                                            val errorMessage =
                                                exception.localizedMessage ?: "An error occurred"
                                            Toast.makeText(
                                                context,
                                                errorMessage,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                } else {
                                    task.exception?.let { exception ->
                                        // Handle other registration errors
                                        val errorMessage =
                                            exception.localizedMessage ?: "An error occurred"
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    isCreatingAccount = false
                                }
                            }
                    } else {
                        Toast.makeText(context, "No field should be Empty!", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row {
                MyNormalText(valueId = R.string.ask_signin)
                Spacer(modifier = Modifier.width(8.dp))
                
//                TextButton(onClick = {
//                    rootNavController.popBackStack()
//                    rootNavController.navigate(LOGIN)
//                }) {
//                    Text(
//                        text = "Log In",
//                        textDecoration = TextDecoration.Underline,
//                        color = MaterialTheme.colorScheme.primary,
//                    )
//                }

                Text(
                    text = "Log In",
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable {
                            rootNavController.popBackStack()
                        }
                )

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