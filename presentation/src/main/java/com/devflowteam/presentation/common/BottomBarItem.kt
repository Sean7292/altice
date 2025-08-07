package com.devflowteam.presentation.component

import androidx.compose.ui.graphics.vector.ImageVector
import com.devflowteam.presentation.navigation.Destination

data class BottomBarItem(
    val title: String,
    val destination: Destination,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
)