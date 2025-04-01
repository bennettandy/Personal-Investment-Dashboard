package com.evel.test_utils.mvi

import com.evel.core.UiText

sealed interface TestUtilsSideEffect {
    data class ShowMessage(val message: UiText): TestUtilsSideEffect
}