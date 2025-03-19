package com.avsoftware.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
sealed class UiText: Parcelable {
    data class UiString(val string: String): UiText()
    data class ResourceString(val resId: Int): UiText()
}

fun String.toUiText() = UiText.UiString(this)
@Deprecated(
    message = "Use Int.toUiText()",
    replaceWith = ReplaceWith("toUiText()", "com.evel.core.ui.Int.toUiText()"),
    level = DeprecationLevel.WARNING
)
fun Int.toResourceString() = UiText.ResourceString(this)
fun Int.toUiText() = UiText.ResourceString(this)
