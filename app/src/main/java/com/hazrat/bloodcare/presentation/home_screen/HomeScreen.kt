package com.hazrat.bloodcare.presentation.home_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hazrat.bloodcare.R
import com.hazrat.bloodcare.navigation.Route
import com.hazrat.bloodcare.presentation.component.ActivityAs
import com.hazrat.bloodcare.presentation.component.HomePageHeaderCard
import com.hazrat.bloodcare.presentation.component.HomePageSectionHeading
import com.hazrat.bloodcare.presentation.component.HomeVerticalGrid
import com.hazrat.bloodcare.presentation.component.RecentlyViewedCards

/**
 * @author Hazrat Ummar Shaikh
 */

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onActivityClick: (ActivityAs) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            HomePageHeaderCard()
        }
        item {
            Spacer(Modifier.height(50.dp))
            HomePageSectionHeading("Activity As")
            HomeVerticalGrid(
                columnCount = 2,
                homeSectionNumber = 1,
                height = 200.dp,
                onActivityClick = { route ->
                    onActivityClick(route)
                }
            )
            HomePageSectionHeading("Blood Group")
            HomeVerticalGrid(
                columnCount = 4,
                homeSectionNumber = 2,
                height = 200.dp,
                onActivityClick = {}
            )
        }
        item {
            Spacer(Modifier.height(10.dp))
            HomePageSectionHeading("Recently Viewed")
            RecentlyViewedCards(
                bloodGroupIcon = R.drawable.b_plus,
                cardText = "Emergency B+ Blood Needed",
                hospitalName = "Hospital Name",
                date = "25 Nov 2025",
                onClick = {}
            )
            RecentlyViewedCards(
                bloodGroupIcon = R.drawable.b_plus,
                cardText = "Emergency B+ Blood Needed",
                hospitalName = "Hospital Name",
                date = "25 Nov 2025",
                onClick = {}

            )
        }
        item {
            Spacer(Modifier.height(10.dp))
            HomePageSectionHeading("Our Contribution")
            HomeVerticalGrid(
                columnCount = 3,
                homeSectionNumber = 3,
                height = 200.dp,
                onActivityClick = {}
            )
        }
    }
}



