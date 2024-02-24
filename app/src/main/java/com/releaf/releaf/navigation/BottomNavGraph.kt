package com.reyaz.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.releaf.releaf.screens.CommunityScreen
import com.releaf.releaf.screens.HomeScreen
import com.releaf.releaf.screens.JournalScreen
import com.releaf.releaf.screens.ProfileScreen
import com.releaf.releaf.screens.ProgressScreen
import com.releaf.releaf.utility.NavConst.COMMUNITY
import com.releaf.releaf.utility.NavConst.HOME
import com.releaf.releaf.utility.NavConst.JOURNAL
import com.releaf.releaf.utility.NavConst.PROFILE
import com.releaf.releaf.utility.NavConst.PROGRESS

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
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
