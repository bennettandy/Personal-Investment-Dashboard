package com.avsoftware.core.ext

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.avsoftware.core.UiText

/**
 * Extension functions to convert UiText to String
 * in UI Modules
 */

fun UiText.displayString(context: Context): String =
    when (this){
        is UiText.UiString -> string
        is UiText.ResourceString -> context.getString(resId)
    }

@Composable
fun UiText.asString(): String {
    val context = LocalContext.current
    return when (this) {
        is UiText.UiString -> this.string
        is UiText.ResourceString -> context.getString(this.resId)
    }
}