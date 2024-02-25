package com.releaf.releaf.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.reyaz.bottomnavigation.BottomBarScreenObj
import com.reyaz.bottomnavigation.MainNavGraph

@Composable
fun MainScreen( navController: NavHostController = rememberNavController() ) {
    Scaffold(
        bottomBar = { MyBottomBar(navController = navController) }
    ) {
        it
        MainNavGraph(navController = navController)
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

    NavigationBar(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp)),
//        containerColor = MaterialTheme.colorScheme.surface
    ) {
        screensList.forEachIndexed { index, screensListItem ->
            NavigationBarItem(
                modifier = Modifier
                    .size(90.dp)
                    .padding(top = 0.dp),
                selected = selectedIndex == index,
                onClick = {
                    navController.navigate(screensListItem.route) {
                        popUpTo(0)
                    }
                    selectedIndex = index
                },
                icon = {
                    Icon(imageVector = screensListItem.icon, contentDescription = null)
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
