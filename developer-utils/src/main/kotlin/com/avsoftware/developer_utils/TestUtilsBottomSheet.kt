package com.evel.test_utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evel.test_utils.mvi.TestUtilsIntent
import com.evel.test_utils.mvi.TestUtilsUiState
import com.avsoftware.developer_utils.ui.DebugInfoView
import com.evel.test_utils.ui.FailureInjectorConfigView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestUtilsBottomSheet(
    uiState: TestUtilsUiState,
    onDismissRequest: () -> Unit,
    handleIntent: (TestUtilsIntent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FailureInjectorConfigView(
                uiState.failureInjectorConfig,
                onConfigChange = {
                    handleIntent(TestUtilsIntent.UpdateFailureConfig(it))
                }
            )

            DebugInfoView(modifier = Modifier, uiState.debugInfoConfig){
                handleIntent(TestUtilsIntent.UpdateDebugInfoConfig(it))
            }
        }
    }
}