package com.avsoftware.search

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel(), ContainerHost<SearchUiState, SearchSideEffect> {

    // container exposes UI state flow and side effect flows
    override val container =
        viewModelScope.container<SearchUiState, SearchSideEffect>(SearchUiState.default)

    fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.SearchTicker -> intent {
                reduce {
                    state.copy( query = intent.searchQuery)
                }
            }
        }
    }
}

interface SearchIntent {
    data class SearchTicker(val searchQuery: String): SearchIntent
}

@Parcelize
data class SearchUiState(
    val query: String
): Parcelable {
    companion object {
        val default = SearchUiState(
            query = ""
        )
    }
}

interface SearchSideEffect {
    data object FixMe: SearchSideEffect
}