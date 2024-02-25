package com.releaf.releaf.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.screens.MainScreen
import com.releaf.releaf.utility.NavConst
import com.releaf.releaf.utility.NavConst.AUTH_ROUTE
import com.releaf.releaf.utility.NavConst.MAIN_ROUTE
import com.releaf.releaf.utility.NavConst.ROOT_ROUTE

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
//        startDestination = AUTH_ROUTE,
        startDestination = MAIN_ROUTE,
        route = ROOT_ROUTE
    ) {

        authNavGraph(navController)
//        mainNavGraph(navController)

        composable(route = MAIN_ROUTE) {
            MainScreen()
        }
    }
}