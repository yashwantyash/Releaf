package com.reyaz.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.releaf.releaf.utility.NavConst.COMMUNITY
import com.releaf.releaf.utility.NavConst.HOME
import com.releaf.releaf.utility.NavConst.JOURNAL
import com.releaf.releaf.utility.NavConst.PROFILE
import com.releaf.releaf.utility.NavConst.PROGRESS

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = HOME,
        title = "Home",
        icon = Icons.Default.Home
    )

    data object Progress : BottomBarScreen(
        route = PROGRESS,
        title = "Progress",
        icon = Icons.Default.DateRange
    )

    data object Journal : BottomBarScreen(
        route = JOURNAL,
        title = "Journal",
        icon = Icons.Default.Menu
    )

    data object Community : BottomBarScreen(
        route = COMMUNITY,
        title = "Community",
        icon = Icons.Default.Add
    )
    data object Profile : BottomBarScreen(
        route = PROFILE,
        title = "Profile",
        icon = Icons.Default.Person
    )
}
