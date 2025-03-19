package com.avsoftware.core.mvi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoadableList<T : Parcelable>(
    val loadingId: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: List<T>? = null,
    val hasEverLoaded: Boolean = false
) : Parcelable {

    // Still handy for checking "pristine" state
    fun isNotLoaded() = !isLoading && !isError && data == null

    // Can we kick off a load?
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

    fun finishLoading(newData: List<T>?) = copy(
        isLoading = false,
        isError = false,
        data = newData
    )

    fun failLoading() = copy(
        isLoading = false,
        isError = true
    )
}