package com.chelo.miramarket.presentation.navigation

import com.chelo.miramarket.presentation.map.MapScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelo.miramarket.presentation.home.HomeScreen
import com.chelo.miramarket.presentation.login.screen.LoginScreen
import com.chelo.miramarket.presentation.splashscreen.SplashScreen


@Composable
fun NavigationWrapper() {
    val context = LocalContext.current
    val navController = rememberNavController()

    NavHost(navController, startDestination = Home.ROUTE) {

        composable(Home.ROUTE) {
            HomeScreen() {
                navController.navigate(Map.ROUTE) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }

            }
        }

        composable(Splash.ROUTE) {
            SplashScreen() {
                navController.navigate(Login.ROUTE) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        }

        composable(Login.ROUTE) {
            LoginScreen() {
                navController.navigate(Home.ROUTE) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        }

        composable(Map.ROUTE) {
            MapScreen(viewContext = context) {
                navController.navigate(Home.ROUTE) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }


        }
    }
}





