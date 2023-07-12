import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import dev.giuliopime.shared.data.model.ZbSettings
import dev.giuliopime.shared.data.repository.impl.DefaultSettingsRepository
import dev.giuliopime.shared.data.source.local.OfflineSettingsStorage
import dev.giuliopime.shared.di.initKoin
import dev.giuliopime.shared.logic.BreakManager
import dev.giuliopime.shared.viewmodel.ZenBreakViewModel
import dev.giuliopime.shared_compose.ZenBreakUi
import java.awt.Dimension
import java.awt.Toolkit

private val koin = initKoin().koin


fun main() = application {
    val trayState = rememberTrayState()
    var isPopupWindowVisible by remember {
        mutableStateOf(false)
    }

    val breakManager: BreakManager = remember {
        DesktopBreakManager(
            breakNotification = {
                if (it.popupNotification) {
                    isPopupWindowVisible = true
                } else {
                    trayState.sendNotification(
                        Notification(
                            title = "Take a quick break",
                            message = it.breakMessage,
                            type = Notification.Type.Info
                        )
                    )
                }
            }
        )
    }

    val viewModel : ZenBreakViewModel = remember {
        ZenBreakViewModel(breakManager)
    }
    val settings = viewModel.zbSettings.collectAsState(ZbSettings())

    var isSettingsWindowVisible by remember(settings.value.hasCompletedFirstRun) {
        mutableStateOf(settings.value.hasCompletedFirstRun)
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
                    viewModel.setEnabled(!settings.value.enabled)
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
        ZenBreakUi(viewModel)
    }

    Window(
        visible = isPopupWindowVisible,
        onCloseRequest = {
            isPopupWindowVisible = false
            breakManager.planBreak(settings.value)
        },
        undecorated = true,
        resizable = false,
        transparent = true,
        alwaysOnTop = true,
        focusable = false,
    ) {
        Toolkit.getDefaultToolkit().screenSize.let {
            val insets = window.insets
            window.size = Dimension(it.width + insets.left + insets.right, it.height + insets.bottom + insets.top)
            window.setLocation(0, 0)
            window.toFront()
            window.requestFocus()
        }

        Column(
            modifier = Modifier.fillMaxSize().border(BorderStroke(2.dp, Color.Red)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(Modifier.background(Color.Cyan)) {
                Button(onClick = {
                    isPopupWindowVisible = false
                    breakManager.planBreak(settings.value)
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