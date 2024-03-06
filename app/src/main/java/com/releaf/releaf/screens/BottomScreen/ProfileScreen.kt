package com.releaf.releaf.screens.BottomScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.NavConst.LOGIN


@Composable
fun ProfileScreen(
    navController: NavHostController,
    navControllerAuth: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                Firebase.auth.signOut()
                    navController.popBackStack()
                    navControllerAuth.popBackStack()
                navController.navigate(LOGIN)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Logout")

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ReLeafTheme {
//        ProfileScreen(navController = rememberNavController())
    }
}


