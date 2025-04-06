package com.avsoftware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.avsoftware.core_ui.theme.DashboardAppTheme
import com.avsoftware.dashboard.DashboardIntent
import com.avsoftware.dashboard.DashboardViewModel
import com.avsoftware.dashboard.screen.DashboardScreen
import com.avsoftware.dashboard.screen.DetailsScreen
import com.avsoftware.splash.SplashScreen
import com.avsoftware.database.AppDatabase
import com.avsoftware.search.StockSymbolsViewModel
import com.avsoftware.search.TickerSearchScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appDatabase: AppDatabase

    private val stockSymbolsViewModel: StockSymbolsViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()

    @OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("App Database $appDatabase")

        setContent {

            DashboardAppTheme {
                val outerNavController = rememberNavController()
                val navController = rememberNavController()

                val stockSymbolUiState = stockSymbolsViewModel.container.stateFlow.collectAsState()

                val dashboardUiState = dashboardViewModel.container.stateFlow.collectAsState()

//                LaunchedEffect(true) {
//                    searchViewModel.handleIntent(StockSymbolsIntent.SearchTicker("TSLA"))
//                }

                SharedTransitionLayout() {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize(),
                        navController = outerNavController,
                        startDestination = "splash"
                    ) {
                        composable(route = "splash") {
                            SplashScreen(
                                splashCompleted = {
                                    outerNavController.navigate("main"){
                                        popUpTo("splash") { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(route = "search"){
                                backStackEntry ->
                            TickerSearchScreen(
                                stockSymbolList = stockSymbolUiState.value.tickerList
                            )
                        }

                        composable(route = "main") {
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        title = { Text("Personal Investment Dashboard") },
                                        navigationIcon = handleNavigation(navController)
                                    )
                                },
                                bottomBar = {
                                    BottomAppBar {  }
                                }
                            ) {
                                NavHost(
                                    modifier = Modifier.padding(it),
                                    navController = navController,
                                    startDestination = "dashboard"
                                ) {
                                    composable(route = "dashboard") {

                                        LaunchedEffect(true) {
                                            Timber.d("Refreshing Crypto")
                                            delay(3000L)
                                            dashboardViewModel.handleIntent(DashboardIntent.RefreshCryptoCurrencies)
                                        }

                                        DashboardScreen(
                                            uiState = dashboardUiState.value,
                                            navigateToDetails = { navController.navigate(route = "details-screen") }
                                        )
                                    }

                                    composable(route = "details-screen") {
                                        DetailsScreen(
                                            navigateToDashboard = {
                                                navController.navigate(
                                                    route = "dashboard"
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        composable(route = "login-signup") {
                            Text("Login/Signup")
                        }
                    }
                }
            }
        }
    }

    private fun handleNavigation(navController: NavHostController): @Composable () -> Unit =
        {
            Timber.d("Backstack: ${navController.currentBackStackEntryAsState()}")
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }

}
