package com.releaf.releaf.screens.profilefeature

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.releaf.releaf.R
import com.releaf.releaf.components.LoadingScreen
import com.releaf.releaf.components.MyInputField

@Composable
fun EditProfile(
    navController: NavController
) {
    val context = LocalContext.current
    val userFirebaseAuth = FirebaseAuth.getInstance().currentUser
    var isConnected by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(true) }
    var isSaving by remember { mutableStateOf(false) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf(userFirebaseAuth?.email ?: "") }
    var password by remember { mutableStateOf("") }

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    var isConnected = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true

    isConnected = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (networkCapabilities != null) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                true
            } else if (
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                true
            } else {
                false
            }
        } else {
            false
        }
    } else {
        true
    }

    if (!isConnected) {
        LoadingScreen(false)
    } else {
        if (isLoading) {
            LoadingScreen()
            val uId = userFirebaseAuth?.uid
            if (uId != null) {
                val fireDbUsersRef = FirebaseDatabase.getInstance().getReference("user").child(uId)
                var name by remember { mutableStateOf("") }
                fireDbUsersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(UserDb::class.java)
                        name = user?.fullName ?: ""
//                        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
                        fullName = name
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.toException().toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            isLoading = false
        } else {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .size(46.dp)
                            .clickable { navController.popBackStack() }
                    )
                    Text(text = "Edit Profile", fontSize = 28.sp)
                }

                Spacer(Modifier.height(100.dp))

                OutlinedTextField(
                    value = fullName,
                    onValueChange = {
                        fullName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = {
                        Text(text = stringResource(R.string.fullname))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (fullName.isNotBlank()) {
                            IconButton(
                                onClick = {
                                    fullName = ""
                                }
                            ) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                )

                MyInputField(
                    label = R.string.email,
                    leadIcon = Icons.Default.Email,
                    initialValue = email,
                    keyboardType = KeyboardType.Email,
                    capitalization = KeyboardCapitalization.None,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    email = it
                }
                MyInputField(
                    label = R.string.current_password,
                    leadIcon = Icons.Default.Lock,
                    initialValue = password,
                    keyboardType = KeyboardType.Password,
                    capitalization = KeyboardCapitalization.None,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    password = it
                }
                if (isSaving) {
                    LinearProgressIndicator(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp))
                } else {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            isSaving = true
                            val credential = EmailAuthProvider
                                .getCredential(userFirebaseAuth?.email!!, password)
                            userFirebaseAuth.reauthenticate(credential)
                                .addOnCompleteListener { reauthResult ->
                                    if (reauthResult.isSuccessful) {
                                        userFirebaseAuth.updateEmail("reyaz@gmail.com")
                                            .addOnCompleteListener { updateEmailResult ->
                                                if (updateEmailResult.isSuccessful) {
                                                    isSaving = false
                                                    Toast.makeText(
                                                        context,
                                                        "Email Updated successfully",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                } else {
                                                    isSaving = false

                                                    val errorMessage =
                                                        updateEmailResult.exception?.message
                                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT)
                                                        .show()
                                                }
                                            }

                                    } else {
                                        isSaving = false
                                        // Reauthentication failed
                                        // Handle the error and inform the user
                                        val errorMessage = reauthResult.exception?.message
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    }
//        fullName = name
                                }
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

class UserDb// No-argument constructor required by Firebase
    () {
    var fullName: String? = null
    var email: String? = null
    var phone: String? = null

}

