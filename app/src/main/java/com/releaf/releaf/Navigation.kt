package com.releaf.releaf

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.releaf.releaf.screens.HomeScreen
import com.releaf.releaf.screens.LoginScreen
import com.releaf.releaf.screens.SignupScreen

@Composable
fun SetupNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  "login"){
        composable(route = "login"){
            LoginScreen(navController)
        }
        composable(route = "signup"){
            SignupScreen(navController)
        }
        composable(route = "home"){
            HomeScreen()
        }
    }
}