package com.avsoftware.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avsoftware.domain.fmp.crypto.CryptoCurrenciesRepository
import com.avsoftware.domain.fmp.crypto.CryptoCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardRoomViewModel
    @Inject constructor(
        private val cryptoCurrenciesRepository: CryptoCurrenciesRepository
    ): ViewModel() {

        val dataFlow: Flow<List<CryptoCurrency>> = cryptoCurrenciesRepository.getDataFlow()

        fun fetchFromNetwork() {
            viewModelScope.launch {
                cryptoCurrenciesRepository.refreshFromApi()
            }
        }
    }