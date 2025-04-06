package com.avsoftware.dashboard

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.core.DomainResult
import com.avsoftware.core.mvi.LoadableList
import com.avsoftware.domain.fmp.crypto.CryptoCurrency
import com.avsoftware.domain.fmp.crypto.GetCryptoCurrenciesUseCase
import com.avsoftware.domain.fmp.search.GetStockSymbolsUseCase
import com.avsoftware.domain.fmp.search.StockSymbolSearch
import com.avsoftware.domain.stocks.GetStocksUseCase
import com.avsoftware.domain.stocks.Stock
import com.avsoftware.model.StockSymbol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val stockSymbolSearch: StockSymbolSearch,
    private val getStockSymbolsUseCase: GetStockSymbolsUseCase,
    private val getStocksUseCase: GetStocksUseCase,
    private val getCryptoCurrenciesUseCase: GetCryptoCurrenciesUseCase
): ViewModel(), ContainerHost<DashboardUiState, DashboardSideEffect> {

    // container exposes UI state flow and side effect flows
    override val container =
        viewModelScope.container<DashboardUiState, DashboardSideEffect>(DashboardUiState.default)

    init {
        Timber.d("SEARCH $stockSymbolSearch")

        viewModelScope.launch {
//            getStockSymbolsUseCase.getSelectedStockSymbols().collect {
//                Timber.d("Got ${it.size} symbols")
//                handleIntent(DashboardIntent.UpdateDashboard(it))
//            }
        }

    }

    fun handleIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.SearchTicker -> handleSearchTicker(intent.searchQuery)
            is DashboardIntent.UpdateDashboard -> handleUpdateStockSymbols(intent.stockSymbols)
            is DashboardIntent.RefreshStocks -> handleRefreshStocks()
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
            is DomainResult.Success -> reduce {
                state.copy(
                    cryptoCurrencies = state.cryptoCurrencies.finishLoading(result.data)
                )
            }

            is DomainResult.Error -> reduce {
                state.copy(
                    cryptoCurrencies = state.cryptoCurrencies.failLoading()
                )
            }
        }
    }

    private fun handleRefreshStocks() = intent {

        reduce {
            state.copy(
                stocks = state.stocks.startLoading()
            )
        }

        when (val result = getStocksUseCase()){
            is DomainResult.Success -> reduce {
                state.copy(
                    stocks = state.stocks.finishLoading(result.data)
                )
            }

            is DomainResult.Error -> reduce {
                state.copy(
                    stocks = state.stocks.failLoading()
                )
            }
        }
    }

    private fun handleUpdateStockSymbols(stockSymbols: List<StockSymbol>) = intent {
        reduce {
            state.copy(
                tickerList = stockSymbols
            )
        }
    }

    private fun handleSearchTicker(searchQuery: String) = intent {

            reduce {
                state.copy( query = searchQuery)
            }

            when (val result = stockSymbolSearch.searchSymbol(searchQuery)){
                is DomainResult.Success -> {
                    Timber.d("Got ${result.data.size} results")
                    reduce {
                        state.copy(
                            tickerList = result.data
                        )
                    }
                }
                is DomainResult.Error -> {
                    Timber.e("Failed ${result.error}")
                    postSideEffect(DashboardSideEffect.FixMe)
                }
            }
        }

}

interface DashboardIntent {
    data object RefreshStocks: DashboardIntent
    data object RefreshCryptoCurrencies: DashboardIntent
    data class SearchTicker(val searchQuery: String): DashboardIntent
    data class UpdateDashboard(val stockSymbols: List<StockSymbol>): DashboardIntent
}

@Parcelize
data class DashboardUiState(
    val query: String,
    val tickerList: List<StockSymbol>,
    val stocks: LoadableList<Stock>,
    val cryptoCurrencies: LoadableList<CryptoCurrency>
): Parcelable {
    companion object {
        val default = DashboardUiState(
            query = "",
            tickerList = emptyList(),
            stocks = LoadableList(),
            cryptoCurrencies = LoadableList()
        )
    }
}

interface DashboardSideEffect {
    data object FixMe: DashboardSideEffect
}