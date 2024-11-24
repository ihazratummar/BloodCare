package com.hazrat.bloodcare.navigation

import com.hazrat.bloodcare.R
import kotlinx.serialization.Serializable

/**
 * @author Hazrat Ummar Shaikh
 */


@Serializable
sealed class Route{
    @Serializable
    data object Home : Route()

    @Serializable
    data object Search: Route()

    @Serializable
    data object More: Route()

    @Serializable
    data object BloodDonor: Route()

    @Serializable
    data object BloodRecepent: Route()

    @Serializable
    data object CreatePost: Route()

    @Serializable
    data object BloodGiven: Route()
}




sealed class BottomNavigation<T>(val icon: Int, val fillIcon: Int,  val name: String, val route: T){
    data object HomeNav: BottomNavigation<Route.Home>(icon = R.drawable.home, fillIcon = R.drawable.homefill, name = "Home", route = Route.Home)
    data object SearchNav: BottomNavigation<Route.Search>(icon = R.drawable.outline_search,fillIcon = R.drawable.search_filled, name = "Search", route = Route.Search)
    data object MoreNav: BottomNavigation<Route.More>(icon = R.drawable.more_outline,fillIcon = R.drawable.more_filled, name = "More", route = Route.More)
}