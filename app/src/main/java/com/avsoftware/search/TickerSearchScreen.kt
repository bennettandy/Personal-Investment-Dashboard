package com.avsoftware.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.avsoftware.data.fmp.search.model.StockSymbol

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickerSearchScreen(
    modifier: Modifier = Modifier,
    stockSymbolList: List<StockSymbol>,
    onTickerSelected: (String) -> Unit = {} // Callback for when a ticker is selected
) {
    // Sample list of ticker symbols (replace with your data source later)
    val tickerSymbols = listOf(
        "AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "FB", "NVDA", "JPM", "V", "WMT"
    )

    // State for the search query
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    // Filtered list based on search query
    val filteredTickers = tickerSymbols.filter {
        it.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { isSearchActive = false }, // Close keyboard/search on explicit search
                active = isSearchActive,
                onActiveChange = { isSearchActive = it },
                placeholder = { Text("Search ticker symbols...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Content shown when SearchBar is active (expanded)
                LazyColumn {
                    items(stockSymbolList) { ticker ->
                        Text(
                            text = ticker.symbol,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    searchQuery = ticker.symbol // Set query to selected ticker
                                    isSearchActive = false // Close search
                                    onTickerSelected(ticker.symbol) // Notify parent
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        // Main content (e.g., full list or results)
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (searchQuery.isEmpty()) {
                Text("Enter a ticker symbol to search", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn {
                    items(stockSymbolList) { stockSymbol ->
                        TickerItem(
                            ticker = stockSymbol.symbol,
                            onClick = { onTickerSelected(stockSymbol.symbol) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TickerItem(
    ticker: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = ticker,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TickerSearchScreenPreview() {
    MaterialTheme {
        TickerSearchScreen(
            stockSymbolList = emptyList()
        )
    }
}