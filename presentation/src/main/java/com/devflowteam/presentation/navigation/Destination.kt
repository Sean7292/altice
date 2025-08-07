package com.devflowteam.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object HomeGraph: Destination

    @Serializable
    data object HomeScreen: Destination {
        override fun toString(): String {
            return "${this::class.qualifiedName}"
        }
    }

    @Serializable
    data object FavoriteScreen: Destination {
        override fun toString(): String {
            return "${this::class.qualifiedName}"
        }
    }

    @Serializable
    data object SettingsScreen: Destination {
        override fun toString(): String {
            return "${this::class.qualifiedName}"
        }
    }

    @Serializable
    data class DetailScreen(val movieId: Long): Destination
}