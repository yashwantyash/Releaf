package com.releaf.releaf.screens.profilefeature

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.releaf.releaf.R
import com.releaf.releaf.components.LoadingScreen
import com.releaf.releaf.components.MyInputField
import com.releaf.releaf.database.ReleafDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun EditProfile(
    navController: NavController
) {
    val context = LocalContext.current
    val userFirebaseAuth = FirebaseAuth.getInstance().currentUser
    var isConnected by remember { mutableStateOf(true) }

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

        var fullName by remember { mutableStateOf(userFirebaseAuth?.displayName ?: "") }
        var email by remember { mutableStateOf(userFirebaseAuth?.email ?: "") }

//        LaunchedEffect(Unit) {
//            val name = withContext(Dispatchers.IO) {
//                ReleafDatabase.getDatabase(context).userDao().getFullName()
//            }
////        fullName = name
//        }
//
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

                MyInputField(
                    label = R.string.fullname,
                    leadIcon = Icons.Default.Person,
                    initialValue = fullName
                ) {
                    fullName = it
                }

                MyInputField(
                    label = R.string.email,
                    leadIcon = Icons.Default.Email,
                    initialValue = email
                ) {
                    email = it
                }

            }
        }
    }

