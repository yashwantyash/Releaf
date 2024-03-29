package com.reyaz.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.releaf.releaf.navigation.homeNavGraph
import com.releaf.releaf.navigation.profileNavGraph
import com.releaf.releaf.screens.AuthScreen.LoginScreen
import com.releaf.releaf.screens.BottomScreen.CommunityScreen
import com.releaf.releaf.screens.BottomScreen.HomeScreen
import com.releaf.releaf.screens.BottomScreen.JournalScreen
import com.releaf.releaf.screens.BottomScreen.ProfileScreen
import com.releaf.releaf.screens.BottomScreen.ProgressScreen
import com.releaf.releaf.utility.Constants.COMMUNITY
import com.releaf.releaf.utility.Constants.HOME
import com.releaf.releaf.utility.Constants.JOURNAL
import com.releaf.releaf.utility.Constants.LOGIN
import com.releaf.releaf.utility.Constants.MAIN_ROUTE
import com.releaf.releaf.utility.Constants.PROFILE
import com.releaf.releaf.utility.Constants.PROFILE_FEATURE_ROUTE
import com.releaf.releaf.utility.Constants.PROGRESS

@Composable
fun MainNavGraph(
    navController: NavHostController,
    navControllerAuth: NavHostController
) {
    NavHost(
        navController = navController,
        route = MAIN_ROUTE,
        startDestination = HOME
    ) {
        composable(route = HOME) {
            HomeScreen(navController = navController)
        }
        composable(route = PROGRESS) {
            ProgressScreen()
        }
        composable(route = JOURNAL) {
            JournalScreen(navController)
        }
        composable(route = COMMUNITY) {
            CommunityScreen()
        }
        composable(route = PROFILE) {
            ProfileScreen(navController = navController, navControllerAuth = navController)
        }
        composable(route = LOGIN) {
            LoginScreen(rootNavController = navController)
        }
        homeNavGraph(navController = navController)
        profileNavGraph(navController = navController)
    }
}
