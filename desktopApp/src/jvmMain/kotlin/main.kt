import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import data.model.ZbSettings
import data.repository.impl.DefaultSettingsRepository
import data.source.local.OfflineSettingsStorage
import kotlinx.coroutines.launch

fun main() = application {
    val scope = rememberCoroutineScope()

    val settingsStorage = remember {
        OfflineSettingsStorage()
    }
    val settingsRepository = remember {
        DefaultSettingsRepository(settingsStorage)
    }

    val settings = settingsRepository.getSettings().collectAsState(ZbSettings())

    var isSettingsWindowVisible by remember(settings.value.hasCompletedFirstRun) {
        mutableStateOf(settings.value.hasCompletedFirstRun)
    }
    var isPopupWindowVisible by remember {
        mutableStateOf(false)
    }
    val trayState = rememberTrayState()


    val breakManager = remember {
        DesktopBreakManager(
            breakNotification = {
                isPopupWindowVisible = true
            }
        )
    }

    Tray(
        state = trayState,
        icon = TrayIcon,
        menu = {
            Item(
                text = if (settings.value.enabled) "Disable" else "Enable",
                onClick = {
                    settingsRepository.setEnabled(!settings.value.enabled)
                }
            )

            Separator()

            Item(
                "Settings",
                onClick = {
                    isSettingsWindowVisible = true
                }
            )

            Item(
                "Quit",
                onClick = ::exitApplication
            )
        }
    )

    Window(
        title = "ZenBreak settings",
        visible = isSettingsWindowVisible,
        onCloseRequest = {
            isSettingsWindowVisible = false
        },
        icon = WindowIcon
    ) {
        MainView(settingsRepository, breakManager)
    }

    Window(
        title = "Time for a break",
        visible = isPopupWindowVisible,
        onCloseRequest = {
            scope.launch {
                breakManager.planBreak(settings.value)
                isPopupWindowVisible = false
            }
        },
        icon = WindowIcon
    ) {
        Column(Modifier.padding(60.dp)) {
            Text(settings.value.breakMessage)
        }
    }
}

object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}

object WindowIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}