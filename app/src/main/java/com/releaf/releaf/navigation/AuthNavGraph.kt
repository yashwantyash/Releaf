package com.releaf.releaf.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.releaf.releaf.screens.AuthScreen.LoginScreen
import com.releaf.releaf.screens.AuthScreen.SignupScreen
import com.releaf.releaf.utility.Constants.AUTH_ROUTE
import com.releaf.releaf.utility.Constants.LOGIN
import com.releaf.releaf.utility.Constants.SIGNUP

fun NavGraphBuilder.authNavGraph(
    rootNavController: NavHostController
) {
    navigation(
        startDestination = LOGIN,
        route = AUTH_ROUTE
    ) {
        composable(route = LOGIN) {
            LoginScreen(rootNavController=rootNavController)
        }
        composable(route = SIGNUP) {
            SignupScreen(rootNavController=rootNavController)
        }
    }
}