package com.releaf.releaf.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.screens.AuthScreen.LoginScreen
import com.releaf.releaf.screens.AuthScreen.SignupScreen
import com.releaf.releaf.utility.NavConst.AUTH_ROUTE
import com.releaf.releaf.utility.NavConst.LOGIN
import com.releaf.releaf.utility.NavConst.SIGNUP

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = LOGIN,
        route = AUTH_ROUTE
    ) {
        composable(route = LOGIN) {
            LoginScreen(navController=navController)
        }
        composable(route = SIGNUP) {
            SignupScreen(navController=navController)
        }
    }
}