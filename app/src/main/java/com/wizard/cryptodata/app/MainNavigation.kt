package com.wizard.cryptodata.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wizard.cryptodata.app.detail.CryptoDetailScreen
import com.wizard.cryptodata.app.list.CryptoListScreen

@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.CryptoListScreen.route
    ) {
        composable(Screens.CryptoListScreen.route) {
            CryptoListScreen(navController)
        }

        composable(Screens.CryptoDetailScreen.route + "/{crypto}",
            arguments = listOf(
                navArgument("crypto") { type = NavType.StringType }
            )
        ) {
            CryptoDetailScreen(navController)
        }

        composable(Screens.ExtraScreen.route) {
            ExtraScreen()
        }
    }
}

sealed class Screens(val route: String) {
    object CryptoListScreen : Screens("crypto_list_screen")
    object CryptoDetailScreen : Screens("crypto_detail_screen")
    object ExtraScreen : Screens("extra_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}