package com.releaf.releaf.screens.AuthScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.releaf.releaf.R
import com.releaf.releaf.components.LogoAndName
import com.releaf.releaf.components.MyInputField
import com.releaf.releaf.components.MyNormalText
import com.releaf.releaf.components.SigninBtn
import com.releaf.releaf.components.UnderlinedText
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.User
import com.releaf.releaf.ui.theme.ReLeafTheme
import com.releaf.releaf.utility.NavConst.MAIN_ROUTE
import com.releaf.releaf.utility.NavConst.SIGNUP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

    val database: ReleafDatabase = ReleafDatabase.getDatabase(context)

    val auth = FirebaseAuth.getInstance()
//     Check if user is signed in (non-null) and update UI accordingly.
    val currentUser = auth.currentUser
    if (currentUser != null) {
        LaunchedEffect(Unit) {
            navController.popBackStack()
            navController.navigate(MAIN_ROUTE)
        }
    }
    else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoAndName(name)
            Spacer(modifier = Modifier.height(32.dp))

            // Email field
            MyInputField(
                label = R.string.email,
                leadIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            ) {
                email = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Password field
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
                    ""
                )
                {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        isCheckingUserExistence = true

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                    retrieveFirebaseUserDetail(
                                        email = email,
                                        context = context,
                                        onSuccess = { user ->
                                            insertUserInRoom(
                                                user = user,
                                                context = context,
                                                roomDatabase = database
                                            )
                                            Toast.makeText(
                                                context,
                                                "User Authenticated",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            navController.popBackStack()
                                            navController.navigate(MAIN_ROUTE)
                                        },
                                        onFailure = {
                                            Toast.makeText(
                                                context,
                                                "Error retrieving user data",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    )
                                } else {

//                                Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show()
                                    task.exception?.let { exception ->
                                        // Handle other registration errors
                                        val errorMessage =
                                            exception.localizedMessage ?: "An error occurred"
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT)
                                            .show()
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
                    desScreen = SIGNUP
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

private fun retrieveFirebaseUserDetail(
    email: String,
    context: Context,
    onSuccess: (User) -> Unit,
    onFailure: () -> Unit
) {
    val userReference = FirebaseDatabase.getInstance().reference
    val query = userReference.child("user").orderByChild("email").equalTo(email)
    query.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (userSnapshot in dataSnapshot.children) {
                val fullName = userSnapshot.child("fullName").getValue(String::class.java)
                val phone = userSnapshot.child("phone").getValue(String::class.java)
                val user = if (fullName != null && phone != null) User(
                    0,
                    fullName,
                    phone,
                    email
                ) else User(0, "", "", "")
                onSuccess(user)
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors
            Log.e("FirebaseError", "Error retrieving data: ${databaseError.message}")
            onFailure()
        }
    })
}

// Function to insert user data into the database
private fun insertUserInRoom(user: User, context: Context, roomDatabase: ReleafDatabase) {
//    val database: ReleafDatabase = ReleafDatabase.getDatabase(context)
    GlobalScope.launch(Dispatchers.IO) {
        roomDatabase.userDao().insertUser(user)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    ReLeafTheme {
//        LoginScreen(navController = navController, database = ReleafDatabase, name = "Releaf")
    }
}