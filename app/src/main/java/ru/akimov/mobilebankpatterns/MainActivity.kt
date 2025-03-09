package ru.akimov.mobilebankpatterns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.akimov.mobilebankpatterns.feature.accountDetails.AccountDetailsScreen
import ru.akimov.mobilebankpatterns.feature.loanDetails.LoanDetailScreen
import ru.akimov.mobilebankpatterns.feature.login.LoginScreen
import ru.akimov.mobilebankpatterns.feature.main.MainScreen
import ru.akimov.mobilebankpatterns.ui.theme.MobileBankPatternsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileBankPatternsTheme {
                AppNavigation()
            }
        }
    }
}

object Destinations {
    const val LOGIN = "LOGIN"
    const val MAIN = "MAIN"
    const val ACCOUNT_DETAILS = "ACCOUNT_DETAILS"
    const val LOAN_DETAIL = "LOAN_DETAIL"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.LOGIN
    ) {
        composable(route = Destinations.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(route = Destinations.MAIN) {
            MainScreen(
                onNavigateToAccountDetails = {
                    navController.navigate(Destinations.ACCOUNT_DETAILS + "/${it}")
                },
                onNavigateToCreditDetails = { name, balance ->
                    navController.navigate(Destinations.LOAN_DETAIL + "/$name" + "/$balance")
                }
            )
        }

        composable(
            route = Destinations.ACCOUNT_DETAILS + "/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            val name = checkNotNull(it.arguments?.getString("name"))

            AccountDetailsScreen(navController = navController, accountName = name)
        }

        composable(
            route = Destinations.LOAN_DETAIL + "/{name}" + "/{balance}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("balance") { type = NavType.StringType },
            )
        ) {
            val args = checkNotNull(it.arguments)

            LoanDetailScreen(
                name = args.getStringNotNull("name"),
                balance = args.getStringNotNull("balance"),
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}

private fun Bundle.getStringNotNull(key: String): String {
    return checkNotNull(getString(key))
}

