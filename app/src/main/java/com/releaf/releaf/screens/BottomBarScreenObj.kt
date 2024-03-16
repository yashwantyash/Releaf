package com.reyaz.bottomnavigation

import com.releaf.releaf.R
import com.releaf.releaf.utility.Constants.COMMUNITY
import com.releaf.releaf.utility.Constants.HOME
import com.releaf.releaf.utility.Constants.JOURNAL
import com.releaf.releaf.utility.Constants.PROFILE
import com.releaf.releaf.utility.Constants.PROGRESS

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
