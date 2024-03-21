package com.releaf.releaf.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reyaz.bottomnavigation.BottomBarScreenObj
import com.reyaz.bottomnavigation.MainNavGraph

@Composable
fun MainScreen(rootNavController : NavHostController, navController: NavHostController = rememberNavController() ) {
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isFABVisible by rememberSaveable {
        mutableStateOf(true)
    }
    Scaffold(
        bottomBar = { MyBottomBar(navController = navController) },

            floatingActionButton = {
                if(isFABVisible) {
                FloatingActionButton(onClick = { isSheetOpen = true }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        }
    ) {
        it
        MainNavGraph(navControllerAuth = rootNavController, navController = navController)
        BottomSheetContent(isSheetOpen = isSheetOpen, navController) {
            isSheetOpen = false
        }
    }
    DisposableEffect(navController) {
        val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
            // Show FAB only on the home screen
            isFABVisible = destination.route == BottomBarScreenObj.Home.route
        }
        navController.addOnDestinationChangedListener(callback)
        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }
}

@Composable
fun MyBottomBar(navController: NavHostController) {

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val screensList = listOf(
        BottomBarScreenObj.Home,
        BottomBarScreenObj.Progress,
        BottomBarScreenObj.Journal,
        BottomBarScreenObj.Community,
        BottomBarScreenObj.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screensList.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp)),
//        containerColor = MaterialTheme.colorScheme.surface
    ) {
            screensList.forEachIndexed { index, screensListItem ->
                NavigationBarItem(
                    modifier = Modifier
//                        .size(90.dp)
                        .padding(top = 0.dp),
//                    selected = selectedIndex == index,

                    onClick = {
                        navController.navigate(screensListItem.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screensListItem.route
                    } == true,

                    icon = {
                        Icon(
                            painterResource(screensListItem.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(text = screensListItem.title)
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
//                    indicatorColor = Color.Transparent
//                    unselectedIconColor = Color.Gray
                    )
                )
            }
        }
    }
}
