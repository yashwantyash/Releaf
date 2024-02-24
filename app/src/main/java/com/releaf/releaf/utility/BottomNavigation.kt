package com.releaf.releaf.utility

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.screens.HomeBottomScreen
import com.releaf.releaf.screens.HomeScreen
import com.releaf.releaf.screens.LoginScreen
import com.releaf.releaf.screens.ProfileBottomScreen
import com.releaf.releaf.screens.SignupScreen

@Composable
fun SetupBottomNavigation(){
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile
    )

    NavHost(navController = navController, startDestination =  BottomNavItem.Home.route){
        composable(BottomNavItem.Home.route){
            HomeBottomScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileBottomScreen()
        }
    }
//    BottomNavigation {
//        items.forEach { item ->
//            BottomNavigationItem(
//                selected = navController.currentBackStackEntry?.destination?.route == item.route,
//                onClick = {
//                    navController.navigate(item.route)
//                },
//                label = { Text(text = item.label) }
//            )
//        }
//    }
}