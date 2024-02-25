package com.reyaz.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.releaf.releaf.utility.NavConst.COMMUNITY
import com.releaf.releaf.utility.NavConst.HOME
import com.releaf.releaf.utility.NavConst.JOURNAL
import com.releaf.releaf.utility.NavConst.PROFILE
import com.releaf.releaf.utility.NavConst.PROGRESS

sealed class BottomBarScreenObj(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreenObj(
        route = HOME,
        title = "Home",
        icon = Icons.Default.Home
    )

    data object Progress : BottomBarScreenObj(
        route = PROGRESS,
        title = "Progress",
        icon = Icons.Default.DateRange
    )

    data object Journal : BottomBarScreenObj(
        route = JOURNAL,
        title = "Journal",
        icon = Icons.Default.Menu
    )

    data object Community : BottomBarScreenObj(
        route = COMMUNITY,
        title = "Community",
        icon = Icons.Default.Add
    )
    data object Profile : BottomBarScreenObj(
        route = PROFILE,
        title = "Profile",
        icon = Icons.Default.Person
    )
}
