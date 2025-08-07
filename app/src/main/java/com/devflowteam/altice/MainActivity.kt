package com.devflowteam.altice

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.devflowteam.presentation.common.BottomAppBar
import com.devflowteam.presentation.navigation.Destination
import com.devflowteam.presentation.ui.theme.AlticeTheme
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            AlticeTheme {
                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            currentRoute = currentRoute,
                            onNavigateAction = { destination ->
                                navController.navigate(destination)
                            }
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surface
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destination.HomeGraph,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        navigation<Destination.HomeGraph>(
                            startDestination = Destination.HomeScreen
                        ) {
                            composable<Destination.HomeScreen> {

                            }
                            composable<Destination.FavoriteScreen> {

                            }
                            composable<Destination.SettingsScreen> {

                            }
                            composable<Destination.DetailScreen> {
                                val args = it.toRoute<Destination.DetailScreen>()


                            }
                        }
                    }
                }
            }
        }
    }
}