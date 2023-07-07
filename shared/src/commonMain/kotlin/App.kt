import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.pages.AppearancePage
import ui.pages.BehaviourPage
import ui.pages.SystemPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val settingsRepository = remember { SettingsRepository() }

    var tabIndex by remember { mutableStateOf(0) }
    val titles = listOf("Behaviour", "Appearance", "System")

    var enabled by remember { mutableStateOf(true) }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("ZenBreak", style = MaterialTheme.typography.titleLarge)
                    },
                    actions = {
                        Switch(
                            checked = enabled,
                            onCheckedChange = {
                                enabled = it
                            }
                        )
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Crossfade(targetState = enabled) {
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
                                    0 -> BehaviourPage(settingsRepository)
                                    1 -> AppearancePage(settingsRepository)
                                    2 -> SystemPage()
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

expect fun getPlatformName(): String