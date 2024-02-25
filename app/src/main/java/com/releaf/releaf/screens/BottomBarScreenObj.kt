package com.reyaz.bottomnavigation

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.releaf.releaf.R
import com.releaf.releaf.utility.NavConst.COMMUNITY
import com.releaf.releaf.utility.NavConst.HOME
import com.releaf.releaf.utility.NavConst.JOURNAL
import com.releaf.releaf.utility.NavConst.PROFILE
import com.releaf.releaf.utility.NavConst.PROGRESS

sealed class BottomBarScreenObj(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object Home : BottomBarScreenObj(
        route = HOME,
        title = "Home",
        icon =  R.drawable.home
    )

    data object Progress : BottomBarScreenObj(
        route = PROGRESS,
        title = "Progress",
        icon =  R.drawable.progress
    )

    data object Journal : BottomBarScreenObj(
        route = JOURNAL,
        title = "Journal",
        icon =  R.drawable.journal
    )

    data object Community : BottomBarScreenObj(
        route = COMMUNITY,
        title = "Community",
        icon =  R.drawable.community
    )
    data object Profile : BottomBarScreenObj(
        route = PROFILE,
        title = "Profile",
        icon =  R.drawable.person_black
    )
}
