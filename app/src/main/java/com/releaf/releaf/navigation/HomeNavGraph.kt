package com.releaf.releaf.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.releaf.releaf.screens.homefeature.JournalCheckbox
import com.releaf.releaf.screens.homefeature.DailyCheckIn
import com.releaf.releaf.utility.Constants.ADD_JOURNAL_CHECKBOX
import com.releaf.releaf.utility.Constants.CHECKIN
import com.releaf.releaf.utility.Constants.HOME_FEAT_ROUTE
import com.releaf.releaf.utility.Constants.SUPPORT

fun NavGraphBuilder.homeNavGraph(navController: NavHostController){
    navigation(
        route = HOME_FEAT_ROUTE,
        startDestination = CHECKIN
    ) {
        composable(route = CHECKIN) {
            DailyCheckIn(navController)
        }
        composable(route = ADD_JOURNAL_CHECKBOX) {
            JournalCheckbox( navController = navController)
        }
        composable(route =  SUPPORT) {

        }

    }
}