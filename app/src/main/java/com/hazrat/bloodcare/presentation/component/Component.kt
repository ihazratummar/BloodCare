package com.hazrat.bloodcare.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hazrat.bloodcare.R
import com.hazrat.bloodcare.navigation.Route

/**
 * @author Hazrat Ummar Shaikh
 */


@Composable
fun HomePageHeaderCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(250.dp)
            .padding(bottom = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.home_header))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                ProfileAndIcons(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        OutlinedTextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .align(Alignment.BottomCenter),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.outline_search),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(30.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ),
            placeholder = { Text("Search Blood") },
            singleLine = true
        )

    }
}

@Composable
fun ProfileAndIcons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle, contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                text = "Hazrat Ummar Sk",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        append("Donate Blood: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("Off")
                    }
                }
            )
        }
        Spacer(Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.email), null,
        )
        Spacer(Modifier.width(10.dp))
        Icon(painter = painterResource(R.drawable.notificationonn), null)
    }
}


@Composable
fun HomeVerticalGrid(
    columnCount: Int,
    homeSectionNumber: Int,
    height: Dp,
    onActivityClick: (ActivityAs) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount),
        modifier = Modifier
            .height(height)
            .padding(horizontal = 20.dp)
    ) {

        when (homeSectionNumber) {
            1 -> {
                val activity = listOf(
                    ActivityAs.BloodDonor,
                    ActivityAs.BloodRecepent,
                    ActivityAs.CreatePost,
                    ActivityAs.BloodGiven,
                )
                activity.forEach {
                    item {
                        ActivityCards(
                            icon = it.icon,
                            title = it.title,
                            subText = it.subText,
                        ) {
                            onActivityClick(it)
                        }
                    }
                }
            }

            2 -> {
                val bloodList = listOf(
                    BloodGroup.APositive,
                    BloodGroup.ANegative,
                    BloodGroup.BPositive,
                    BloodGroup.BNegative,
                    BloodGroup.ABPositive,
                    BloodGroup.ABNegative,
                    BloodGroup.OPositive,
                    BloodGroup.ONegative,
                )
                bloodList.forEach {
                    item {
                        BloodGroupCard(icon = it.icon)
                    }
                }
            }

            3 -> {
                val contributionList = listOf(
                    Contribution.BloodDonor,
                    Contribution.PostEveryDay,
                    Contribution.PostEveryDay1,
                    Contribution.BloodDonor1,
                    Contribution.PostEveryDay2,
                    Contribution.PostEveryDay3,
                )
                contributionList.forEach {
                    item {
                        ContributionCard(
                            cardColor = it.cardColor,
                            textColor = it.textColor,
                            text = it.text,
                            number = it.numbers
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun BloodGroupCard(
    modifier: Modifier = Modifier,
    icon: Int,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = Color.Black.copy(0.5f))
    ) {
        Icon(
            painter = painterResource(icon), contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(74.dp)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActivityCards(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    subText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .combinedClickable(
                onClick = { onClick.invoke() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(vertical = 10.dp, horizontal = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = Color.Black.copy(0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(34.dp),
                tint = Color.Unspecified
            )
            Spacer(Modifier.width(10.dp))
            Column() {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(5.dp))
                Text(text = subText)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecentlyViewedCards(
    bloodGroupIcon: Int,
    cardText: String,
    hospitalName: String,
    date: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .combinedClickable(
                onClick = { onClick() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(0.5f))
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Card(
                modifier = Modifier.padding(4.dp),
                shape = CircleShape,
                border = BorderStroke(width = 1.dp, color = Color(0xfffa3741)),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(bloodGroupIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(44.dp),
                    tint = Color.Unspecified,
                )
            }
            Spacer(Modifier.width(5.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = cardText,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.blood_given),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text(hospitalName)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.time),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Text(date)
                }
            }
        }
    }
}

@Composable
fun ContributionCard(
    modifier: Modifier = Modifier,
    cardColor: Color,
    number: String,
    text: String,
    textColor: Color
) {
    Card(
        modifier = modifier.padding(horizontal = 5.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = number,
                style = MaterialTheme.typography.displaySmall,
                color = textColor
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }
    }
}


sealed class Contribution(
    val numbers: String,
    val text: String,
    val cardColor: Color,
    val textColor: Color
) {
    data object BloodDonor : Contribution(
        numbers = "1K+",
        text = "Blood Donor",
        cardColor = Color(0xffeaf6fd),
        textColor = Color(0xff75c7f8)
    )

    data object PostEveryDay : Contribution(
        numbers = "20",
        text = "Post EveryDay",
        cardColor = Color(0xffeafcf2),
        textColor = Color(0xff5be194)
    )

    data object PostEveryDay1 : Contribution(
        numbers = "20",
        text = "Post EveryDay",
        cardColor = Color(0xffededfd),
        textColor = Color(0xff717af7)
    )

    data object BloodDonor1 : Contribution(
        numbers = "1K+",
        text = "Blood Donor",
        cardColor = Color(0xfffcedfc),
        textColor = Color(0xffef84eb)
    )

    data object PostEveryDay2 : Contribution(
        numbers = "20",
        text = "Post EveryDay",
        cardColor = Color(0xfffdeeee),
        textColor = Color(0xfff59194)
    )

    data object PostEveryDay3 : Contribution(
        numbers = "20",
        text = "Post EveryDay",
        cardColor = Color(0xfffef6e9),
        textColor = Color(0xfffac25e)
    )
}


@Composable
fun HomePageSectionHeading(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}


sealed class BloodGroup(val name: String, val icon: Int) {
    data object APositive : BloodGroup(name = "A+", icon = R.drawable.a_plus)
    data object ANegative : BloodGroup(name = "A-", icon = R.drawable.a_minus)
    data object BPositive : BloodGroup(name = "B+", icon = R.drawable.b_plus)
    data object BNegative : BloodGroup(name = "B-", icon = R.drawable.b_minus)
    data object ABPositive : BloodGroup(name = "AB+", icon = R.drawable.ab_plus)
    data object ABNegative : BloodGroup(name = "AB-", icon = R.drawable.ab_minus)
    data object OPositive : BloodGroup(name = "O+", icon = R.drawable.o_plus)
    data object ONegative : BloodGroup(name = "O-", icon = R.drawable.o_minus)
}

sealed class ActivityAs(
    val icon: Int,
    val title: String,
    val subText: String,
    val route: Route
) {
    data object BloodDonor : ActivityAs(
        icon = R.drawable.blood_donor,
        title = "Blood Donor",
        subText = "120 Posts",
        route = Route.BloodDonor
    )

    data object BloodRecepent : ActivityAs(
        icon = R.drawable.blood_recepent,
        title = "Blood Recepent",
        subText = "120 Posts",
        route = Route.BloodRecepent
    )

    data object CreatePost : ActivityAs(
        icon = R.drawable.create_post,
        title = "Create Post",
        subText = "It's Easy! 3 step",
        route = Route.CreatePost
    )

    data object BloodGiven : ActivityAs(
        icon = R.drawable.blood_given,
        title = "Blood Given",
        subText = "It's Easy! 1 step",
        route = Route.BloodGiven
    )
}
