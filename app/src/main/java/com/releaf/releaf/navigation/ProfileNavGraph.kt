package com.releaf.releaf.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.releaf.releaf.screens.profilefeature.EditProfile
import com.releaf.releaf.utility.Constants.EDIT_PROFILE
import com.releaf.releaf.utility.Constants.PROFILE
import com.releaf.releaf.utility.Constants.PROFILE_FEATURE_ROUTE

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController
){
    navigation(
        route = PROFILE_FEATURE_ROUTE,
        startDestination = EDIT_PROFILE
    ){
        composable(route = EDIT_PROFILE){
            EditProfile(navController)
        }
    }
}