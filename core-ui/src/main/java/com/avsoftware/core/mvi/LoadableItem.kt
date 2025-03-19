package com.avsoftware.core.mvi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoadableItem<T : Parcelable>(
    val loadingId: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: T? = null,
    val hasEverLoaded: Boolean = false
) : Parcelable {

    fun isNotLoaded() = !isLoading && !isError && data == null

    fun shouldTriggerLoad() = !isLoading && !hasEverLoaded

    override fun toString(): String =
        when {
            isLoading -> "[Loading]"
            isError -> "[Error]"
            else -> "[$data]"
        }

    fun startLoading(newLoadingId: String? = loadingId) = copy(
        isLoading = true,
        isError = false,
        hasEverLoaded = true, // Mark it as touched
        loadingId = newLoadingId
    )

    fun finishLoading(newData: T?) = copy(
        isLoading = false,
        isError = false,
        data = newData
    )

    fun failLoading() = copy(
        isLoading = false,
        isError = true
    )
}