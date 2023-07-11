import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import data.model.ZbSettings
import data.repository.impl.DefaultSettingsRepository
import data.source.local.OfflineSettingsStorage
import kotlinx.coroutines.launch
import java.awt.Dimension
import java.awt.Toolkit


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

    val popupWindowState = remember {
        WindowState(
            position = WindowPosition(Alignment.Center)
        )
    }
    var isPopupWindowVisible by remember {
        mutableStateOf(true)
    }

    val trayState = rememberTrayState()


    val breakManager = remember {
        DesktopBreakManager(
            breakNotification = {
                if (settings.value.popupNotification) {
                    isPopupWindowVisible = true
                } else {
                    trayState.sendNotification(
                        Notification(
                            title = "Take a quick break",
                            message = settings.value.breakMessage,
                            type = Notification.Type.Info
                        )
                    )
                }
            }
        )
    }

    Tray(
        state = trayState,
        icon = TrayIcon,
        onAction = {
            isSettingsWindowVisible = true
        },
        menu = {
            Item(
                text = if (settings.value.enabled) "Disable" else "Enable",
                onClick = {
                    val enabled = !settings.value.enabled
                    settingsRepository.setEnabled(enabled)

                    if (enabled)
                        breakManager.planBreak(settings.value)
                    else
                        breakManager.cancelBreak()
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
        visible = isPopupWindowVisible,
        onCloseRequest = {
            scope.launch {
                breakManager.planBreak(settings.value)
                isPopupWindowVisible = false
            }
        },
        undecorated = true,
        resizable = false,
        transparent = true,
        alwaysOnTop = true,
        focusable = false,
        state = popupWindowState
    ) {
        Toolkit.getDefaultToolkit().screenSize.let {
            val insets = window.insets
            window.size = Dimension(it.width + insets.left + insets.right, it.height + insets.bottom + insets.top)
            window.setLocation(0, 0)
            window.toFront()
        }



        Column(
            modifier = Modifier.fillMaxSize().border(BorderStroke(2.dp, Color.Red)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(Modifier.background(Color.Cyan)) {
                Button(onClick = {
                    scope.launch {
                        breakManager.planBreak(settings.value)
                        isPopupWindowVisible = false
                    }
                }) {

                }
            }
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