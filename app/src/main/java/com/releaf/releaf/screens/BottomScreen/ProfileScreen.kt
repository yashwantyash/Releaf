package com.releaf.releaf.screens.BottomScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.releaf.releaf.components.OutlinedBtn
import com.releaf.releaf.components.ProfileRow
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.Constants.EDIT_PROFILE
import com.releaf.releaf.utility.Constants.LOGIN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun ProfileScreen(
    navController: NavHostController,
    navControllerAuth: NavHostController
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
       val name  = withContext(Dispatchers.IO) {
            ReleafDatabase.getDatabase(context).userDao().getFullName()
        }
        fullName = name
//        Toast.makeText(context, fullName, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(20.dp),
            contentAlignment = Alignment.BottomStart

        ) {
            Column {
                Text(text = "Welcome", fontSize = 44.sp, fontWeight = FontWeight.Bold)
                Text(text = fullName, fontSize = 24.sp, modifier = Modifier.padding(top = 4.dp))
            }
        }
        Text(
            text = "Account Information",
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        ProfileRow(
            title = "Edit Profile",
            onclick = {
                navController.navigate(EDIT_PROFILE)
            }
        )
        ProfileRow(
            title = "Change Password",
            onclick = {

            }
        )

        Spacer(modifier = Modifier.weight(1f))
        OutlinedBtn(
            textOutl = "Logout", outOnClick = {
                Firebase.auth.signOut()
                navController.popBackStack()
                navControllerAuth.popBackStack()
                navController.navigate(LOGIN)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ReLeafTheme {
        val navController = rememberNavController()
        ProfileScreen(navController = navController, navControllerAuth = navController)
    }
}