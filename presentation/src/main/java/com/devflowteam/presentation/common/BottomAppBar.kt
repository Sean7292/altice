package com.devflowteam.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.devflowteam.presentation.R
import com.devflowteam.presentation.component.BottomBarItem
import com.devflowteam.presentation.navigation.Destination
import com.devflowteam.presentation.ui.theme.Dimens

@Composable
fun BottomAppBar(
    currentRoute: String?,
    onNavigateAction: (Destination) -> Unit
) {
    val items = listOf(
        BottomBarItem(
            title = stringResource(R.string.home),
            destination = Destination.HomeScreen,
            activeIcon = Icons.Rounded.Home,
            inactiveIcon = Icons.Outlined.Home
        ),
        BottomBarItem(
            title = stringResource(R.string.favorite),
            destination = Destination.FavoriteScreen,
            activeIcon = Icons.Rounded.Favorite,
            inactiveIcon = Icons.Outlined.FavoriteBorder
        ),
        BottomBarItem(
            title = stringResource(R.string.settings),
            destination = Destination.SettingsScreen,
            activeIcon = Icons.Rounded.Settings,
            inactiveIcon = Icons.Outlined.Settings
        )
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        items.forEach { item ->
            val isSelected = item.destination.toString() == currentRoute
            val icon = if (isSelected) item.activeIcon else item.inactiveIcon

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onNavigateAction(item.destination)
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(Dimens.BottomBarIconSize)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme
                            .typography
                            .labelLarge
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.outline,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.outline,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.8f
                    )
                )
            )
        }
    }
}