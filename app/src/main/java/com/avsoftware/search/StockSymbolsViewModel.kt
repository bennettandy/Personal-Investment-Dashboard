package com.avsoftware.search

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.core.DomainResult
import com.avsoftware.data.fmp.search.GetStockSymbolsUseCase
import com.avsoftware.data.fmp.search.StockSymbolSearch
import com.avsoftware.data.fmp.search.model.StockSymbol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StockSymbolsViewModel @Inject constructor(
    private val stockSymbolSearch: StockSymbolSearch,
    private val getStockSymbolsUseCase: GetStockSymbolsUseCase
): ViewModel(), ContainerHost<StockSymbolsUiState, StockSymbolsSideEffect> {

    // container exposes UI state flow and side effect flows
    override val container =
        viewModelScope.container<StockSymbolsUiState, StockSymbolsSideEffect>(StockSymbolsUiState.default)

    init {
        Timber.d("SEARCH $stockSymbolSearch")

        viewModelScope.launch {
            getStockSymbolsUseCase.getSelectedStockSymbols().collect {
                Timber.d("Got ${it.size} symbols")
                handleIntent(StockSymbolsIntent.UpdateStockSymbols(it))
            }
        }

    }

    fun handleIntent(intent: StockSymbolsIntent) {
        when (intent) {
            is StockSymbolsIntent.SearchTicker -> handleSearchTicker(intent.searchQuery)
            is StockSymbolsIntent.UpdateStockSymbols -> handleUpdateStockSymbols(intent.stockSymbols)
        }
    }

    private fun handleUpdateStockSymbols(stockSymbols: List<StockSymbol>) = intent {
        reduce {
            state.copy(
                tickerList = stockSymbols
            )
        }
    }

    private fun handleSearchTicker(searchQuery: String) = intent{

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
                    postSideEffect(StockSymbolsSideEffect.FixMe)
                }
            }
        }

}

interface StockSymbolsIntent {
    data class SearchTicker(val searchQuery: String): StockSymbolsIntent
    data class UpdateStockSymbols(val stockSymbols: List<StockSymbol>): StockSymbolsIntent
}

@Parcelize
data class StockSymbolsUiState(
    val query: String,
    val tickerList: List<StockSymbol>
): Parcelable {
    companion object {
        val default = StockSymbolsUiState(
            query = "",
            tickerList = emptyList()
        )
    }
}

interface StockSymbolsSideEffect {
    data object FixMe: StockSymbolsSideEffect
}