package com.hazrat.bloodcare.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hazrat.bloodcare.presentation.BloodDonorScreen
import com.hazrat.bloodcare.presentation.BloodGiven
import com.hazrat.bloodcare.presentation.BloodRecepent
import com.hazrat.bloodcare.presentation.CreatePost
import com.hazrat.bloodcare.presentation.home_screen.HomeScreen
import com.hazrat.bloodcare.presentation.more_screen.MoreScreen
import com.hazrat.bloodcare.presentation.search_screen.SearchScreen

/**
 * @author Hazrat Ummar Shaikh
 */

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                navHostController = navHostController
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            modifier = Modifier
                .padding(bottom = bottomPadding),
            navController = navHostController, startDestination = Route.Home,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable<Route.Home> {
                HomeScreen(
                    modifier = modifier,
                    onActivityClick = {activityAs -> 
                        navHostController.navigate(activityAs.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable<Route.BloodDonor> { BloodDonorScreen() }
            composable<Route.BloodRecepent> { BloodRecepent() }
            composable<Route.CreatePost> { CreatePost() }
            composable<Route.BloodGiven> { BloodGiven() }
            composable<Route.Search> { SearchScreen() }
            composable<Route.More> { MoreScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController
) {
    val bottomNavItems = listOf(
        BottomNavigation.HomeNav,
        BottomNavigation.SearchNav,
        BottomNavigation.MoreNav,
    )
    val currentStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = currentStackEntry?.destination
    val isBottomNavVisible =
        bottomNavItems.any { it.route::class.qualifiedName == currentDestination?.route }
    if (isBottomNavVisible) {
        Card(
            modifier = Modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            shape = RectangleShape
        ) {
            NavigationBar(
                modifier = Modifier,
                containerColor = Color.Transparent
            ) {
                bottomNavItems.forEach { item ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == item.route::class.qualifiedName } == true
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navHostController.navigate(item.route) {
                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = if (isSelected) painterResource(id = item.fillIcon) else painterResource(
                                    id = item.icon
                                ),
                                contentDescription = item.name,
                            )
                        },
                        label = { Text(text = item.name) },
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    }

}