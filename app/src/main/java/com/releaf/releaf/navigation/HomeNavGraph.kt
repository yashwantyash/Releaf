package com.releaf.releaf.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.releaf.releaf.models.CheckBoxViewModel
import com.releaf.releaf.screens.homefeature.journal.JournalCheckbox
import com.releaf.releaf.screens.homefeature.DailyCheckIn
import com.releaf.releaf.screens.homefeature.journal.WriteJournal
import com.releaf.releaf.utility.Constants.ADD_JOURNAL_CHECKBOX
import com.releaf.releaf.utility.Constants.CHECKIN
import com.releaf.releaf.utility.Constants.HOME_FEAT_ROUTE
import com.releaf.releaf.utility.Constants.SUPPORT
import com.releaf.releaf.utility.Constants.WRITE_JOURNAL

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
){
    val checkBoxViewModel = CheckBoxViewModel()
    navigation(
        route = HOME_FEAT_ROUTE,
        startDestination = CHECKIN
    ) {
        composable(route = CHECKIN) {
            DailyCheckIn(navController)
        }
        composable(route = ADD_JOURNAL_CHECKBOX) {
            JournalCheckbox( navController = navController, checkBoxViewModel=checkBoxViewModel)
        }
        composable(route = WRITE_JOURNAL) {
            WriteJournal( navController = navController, checkBoxViewModel=checkBoxViewModel)
        }
        composable(route =  SUPPORT) {

        }

    }
}