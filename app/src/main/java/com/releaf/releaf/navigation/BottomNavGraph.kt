package com.reyaz.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.releaf.releaf.screens.BottomScreen.CommunityScreen
import com.releaf.releaf.screens.BottomScreen.HomeScreen
import com.releaf.releaf.screens.BottomScreen.JournalScreen
import com.releaf.releaf.screens.BottomScreen.ProfileScreen
import com.releaf.releaf.screens.BottomScreen.ProgressScreen
import com.releaf.releaf.utility.NavConst.COMMUNITY
import com.releaf.releaf.utility.NavConst.HOME
import com.releaf.releaf.utility.NavConst.JOURNAL
import com.releaf.releaf.utility.NavConst.MAIN_ROUTE
import com.releaf.releaf.utility.NavConst.PROFILE
import com.releaf.releaf.utility.NavConst.PROGRESS

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = MAIN_ROUTE,
        startDestination = HOME
    ) {
        composable(route = HOME) {
            HomeScreen()
        }
        composable(route = PROGRESS) {
            ProgressScreen()
        }
        composable(route = JOURNAL) {
            JournalScreen()
        }
        composable(route = COMMUNITY) {
            CommunityScreen()
        }
        composable(route = PROFILE) {
            ProfileScreen()
        }
    }
}
