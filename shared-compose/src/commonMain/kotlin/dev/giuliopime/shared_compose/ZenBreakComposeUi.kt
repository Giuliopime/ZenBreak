package dev.giuliopime.shared_compose

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared.data.model.ZbSettings
import dev.giuliopime.shared.data.repository.SettingsRepository
import dev.giuliopime.shared.viewmodel.ZenBreakViewModel
import dev.giuliopime.shared_compose.pages.AppearancePage
import dev.giuliopime.shared_compose.pages.BehaviourPage
import dev.giuliopime.shared_compose.pages.SystemPage
import dev.giuliopime.shared_compose.theme.ZenBreakTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZenBreakUi(
    viewModel: ZenBreakViewModel
) {
    val scope = rememberCoroutineScope()
    var tabIndex by remember { mutableStateOf(0) }
    val titles = listOf("Behaviour", "Appearance", "System")

    val settings = viewModel.zbSettings.collectAsState(ZbSettings())

    ZenBreakTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("ZenBreak", style = MaterialTheme.typography.titleLarge)
                    },
                    actions = {
                        Switch(
                            checked = settings.value.enabled,
                            onCheckedChange = {
                                scope.launch {
                                    viewModel.setEnabled(it)
                                }
                            }
                        )
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
            },
            containerColor = MaterialTheme.colorScheme.surface
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Crossfade(targetState = settings.value.enabled) {
                    if (it) {
                        Column {
                            TabRow(
                                selectedTabIndex = tabIndex,
                            ) {
                                titles.forEachIndexed { index, title ->
                                    Tab(
                                        selected = tabIndex == index,
                                        onClick = {
                                            tabIndex = index
                                        },
                                        text = {
                                            Text(
                                                text = title,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier.padding(16.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                when (tabIndex) {
                                    0 -> BehaviourPage(
                                        viewModel = viewModel,
                                        settings = settings.value
                                    )
                                    1 -> AppearancePage(
                                        viewModel = viewModel,
                                        settings = settings.value
                                    )
                                    2 -> SystemPage(
                                        viewModel = viewModel,
                                        settings = settings.value
                                    )
                                }
                            }
                        }
                    } else {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text("ZenBreak is turned off!")
                        }
                    }
                }
            }
        }
    }
}