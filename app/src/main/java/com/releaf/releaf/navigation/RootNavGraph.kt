package com.releaf.releaf.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.screens.MainScreen
import com.releaf.releaf.utility.Constants.AUTH_ROUTE
import com.releaf.releaf.utility.Constants.MAIN_ROUTE
import com.releaf.releaf.utility.Constants.ROOT_ROUTE

@Composable
fun SetupNavigation() {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = AUTH_ROUTE,
//        startDestination = MAIN_ROUTE,
        route = ROOT_ROUTE
    ) {

        authNavGraph(rootNavController = rootNavController)

        composable(route = MAIN_ROUTE) {
            MainScreen(rootNavController = rootNavController)
        }
    }
}