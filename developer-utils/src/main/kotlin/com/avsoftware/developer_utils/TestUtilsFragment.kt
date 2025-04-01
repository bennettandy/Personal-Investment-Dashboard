package com.avsoftware.developer_utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import com.avsoftware.core_ui.theme.DashboardAppTheme


class TestUtilsFragment : DialogFragment() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

//                LaunchedEffect(true) {
//                    appBarViewModel.handleIntent(
//                        AppBarIntent.InitialiseAppBar(
//                            showExternalAppBar = false,
//                            showNotificationBell = false,
//                            title = R.string.test_utils_title.toUiText()
//                        )
//                    )
//                }

//                val uiState = viewModel.container.stateFlow.collectAsState()

                DashboardAppTheme {
//                    TestUtilsBottomSheet(
//                        uiState = uiState.value,
//                        handleIntent = viewModel::handleIntent,
//                        onDismissRequest = { dismiss() }
//                    )
                }
            }
        }
    }
}