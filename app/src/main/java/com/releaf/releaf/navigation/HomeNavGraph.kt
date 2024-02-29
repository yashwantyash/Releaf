package com.releaf.releaf.navigation

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.releaf.releaf.R
import com.releaf.releaf.components.CheckBox
import com.releaf.releaf.screens.homefeature.AddJournalCheckbox
import com.releaf.releaf.screens.homefeature.CheckIn
import com.releaf.releaf.utility.NavConst.ADD_JOURNAL_CHECKBOX
import com.releaf.releaf.utility.NavConst.CHECKIN
import com.releaf.releaf.utility.NavConst.HOME_FEAT_ROUTE
import com.releaf.releaf.utility.NavConst.SUPPORT

fun NavGraphBuilder.homeNavGraph(navController: NavHostController){
    navigation(
        route = HOME_FEAT_ROUTE,
        startDestination = CHECKIN
    ) {
        composable(route = CHECKIN) {
            CheckIn(navController)
        }
        composable(route = ADD_JOURNAL_CHECKBOX) {
            AddJournalCheckbox()
        }
        composable(route =  SUPPORT) {
//            DummyScreen(
//                subtitle = stringResource(id = R.string.deep_detail)
//            )
        }

    }
}