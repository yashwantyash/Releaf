package com.releaf.releaf.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.releaf.releaf.screens.HomeScreen
import com.releaf.releaf.screens.LoginScreen
import com.releaf.releaf.screens.MainScreen
import com.releaf.releaf.screens.ProgressScreen
import com.releaf.releaf.screens.SignupScreen
import com.releaf.releaf.utility.NavConst
import com.releaf.releaf.utility.NavConst.HOME

import com.releaf.releaf.utility.NavConst.LOGIN
import com.releaf.releaf.utility.NavConst.MAIN
import com.releaf.releaf.utility.NavConst.MAIN_ROUTE
import com.releaf.releaf.utility.NavConst.SIGNUP

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = MAIN,
        route = MAIN_ROUTE
    ) {
        composable(route = MAIN) {
            MainScreen()
        }
    }
}
