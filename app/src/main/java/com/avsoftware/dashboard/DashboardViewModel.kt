package com.avsoftware.dashboard

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.core.DomainResult
import com.avsoftware.core.mvi.LoadableList
import com.avsoftware.database.crypto.CryptoCurrencyDao
import com.avsoftware.database.crypto.toEntity
import com.avsoftware.domain.fmp.crypto.CryptoCurrency
import com.avsoftware.domain.fmp.crypto.GetCryptoCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCryptoCurrenciesUseCase: GetCryptoCurrenciesUseCase,
    private val cryptoCurrencyDao: CryptoCurrencyDao
): ViewModel(), ContainerHost<DashboardUiState, DashboardSideEffect> {

    // container exposes UI state flow and side effect flows
    override val container =
        viewModelScope.container<DashboardUiState, DashboardSideEffect>(DashboardUiState.default)

    fun handleIntent(intent: DashboardIntent) {
        when (intent) {
//            is DashboardIntent.SearchTicker -> handleSearchTicker(intent.searchQuery)
//            is DashboardIntent.UpdateDashboard -> handleUpdateStockSymbols(intent.stockSymbols)
//            is DashboardIntent.RefreshStocks -> handleRefreshStocks()
            is DashboardIntent.RefreshCryptoCurrencies -> handleRefreshCryptoCurrencies()
        }
    }

    private fun handleRefreshCryptoCurrencies() = intent {

        reduce {
            state.copy(
                cryptoCurrencies = state.cryptoCurrencies.startLoading()
            )
        }

        when (val result = getCryptoCurrenciesUseCase()){
            is DomainResult.Success -> {
                // update database
                cryptoCurrencyDao.upsertAll(result.data.map { it.toEntity() })

                reduce {
                    state.copy(
                        cryptoCurrencies = state.cryptoCurrencies.finishLoading(result.data.sortedBy { it.symbol })
                    )
                }
            }

            is DomainResult.Error -> reduce {
                state.copy(
                    cryptoCurrencies = state.cryptoCurrencies.failLoading()
                )
            }
        }
    }
}

interface DashboardIntent {
    data object RefreshCryptoCurrencies: DashboardIntent
//    data object RefreshStocks: DashboardIntent
//    data class SearchTicker(val searchQuery: String): DashboardIntent
//    data class UpdateDashboard(val stockSymbols: List<StockSymbol>): DashboardIntent
}

@Parcelize
data class DashboardUiState(
    val query: String,
//    val tickerList: List<StockSymbol>,
//    val stocks: LoadableList<Stock>,
    val cryptoCurrencies: LoadableList<CryptoCurrency>
): Parcelable {
    companion object {
        val default = DashboardUiState(
            query = "",
//            tickerList = emptyList(),
//            stocks = LoadableList(),
            cryptoCurrencies = LoadableList()
        )
    }
}

interface DashboardSideEffect {
    data object FixMe: DashboardSideEffect
}