package com.chelo.miramarket.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelo.miramarket.presentation.home.HomeScreen
import com.chelo.miramarket.presentation.login.LoginScreen
import com.chelo.miramarket.presentation.map.MapScreen
import com.chelo.miramarket.presentation.splashscreen.SplashScreen


@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Splash.ROUTE) {

        composable(Home.ROUTE) {
            HomeScreen() {
                navController.navigate(Map.ROUTE){
                    popUpTo(0){
                        inclusive = true
                    }
                }

            }
        }

        composable(Splash.ROUTE) {
            SplashScreen() {
                navController.navigate(Login.ROUTE){
                    popUpTo(0){
                        inclusive = true
                    }
                }
            }
        }

        composable (Login.ROUTE) {
            LoginScreen(){
                navController.navigate(Home.ROUTE){
                    popUpTo(0){
                        inclusive = true
                    }
                }
            }
        }

        composable (Map.ROUTE){
            MapScreen() {
                TODO()
            }
        }

    }
}





