package com.avsoftware.core.mvi

data class UiPaginatedList<T>(
    val pageSize: Int,
    val nextPage: Int = 0,
    val hasMoreData: Boolean = true,
    val loadingId: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null, // Added for UI feedback
    val data: List<T> = emptyList(),
    val hasEverLoaded: Boolean = false
) {

    fun startLoading(newLoadingId: String? = loadingId) = copy(
        isLoading = true,
        isError = false,
        errorMessage = null,
        hasEverLoaded = true,
        loadingId = newLoadingId
    )

    fun finishLoadingPage(newData: List<T>?) = copy(
        isLoading = false,
        isError = false,
        errorMessage = null,
        data = if (newData.isNullOrEmpty()) data else data + newData,
        hasMoreData = newData?.size == pageSize, // No data or partial page means no more
        nextPage = if (newData?.size == pageSize) nextPage + 1 else nextPage
    )

    fun failLoading(errorMessage: String? = null) = copy(
        isLoading = false,
        isError = true,
        errorMessage = errorMessage
    )

    fun reset() = copy(
        nextPage = 0,
        hasMoreData = true,
        isLoading = false,
        isError = false,
        errorMessage = null,
        data = emptyList(),
        hasEverLoaded = false
    )

    // Utility to check if this is an initial load
    val isInitialLoad: Boolean
        get() = hasEverLoaded && data.isEmpty() && isLoading
}