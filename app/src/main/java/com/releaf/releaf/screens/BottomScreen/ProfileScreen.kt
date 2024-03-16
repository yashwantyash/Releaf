package com.releaf.releaf.screens.BottomScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.releaf.releaf.components.OutlinedBtn
import com.releaf.releaf.theme.ReLeafTheme
import com.releaf.releaf.utility.Constants.LOGIN


@Composable
fun ProfileScreen(
    navController: NavHostController,
    navControllerAuth: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedBtn(
            textOutl = "Logout", outOnClick = {
                Firebase.auth.signOut()
                navController.popBackStack()
                navControllerAuth.popBackStack()
                navController.navigate(LOGIN)
            },
            modifier = Modifier.fillMaxWidth()
        )

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