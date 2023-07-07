import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState

fun main() = application {
    val appRoot = remember { AppRoot() }
    val trayState = rememberTrayState()
    var isSettingsVisible by remember { mutableStateOf(appRoot.isFirstRun()) }

    Tray(
        state = trayState,
        icon = TrayIcon,
        menu = {
            Item(
                text = if (appRoot.isEnabled()) "Disable" else "Enable",
                onClick = {
                    appRoot.toggleEnabled()
                }
            )

            Separator()

            Item(
                "Settings",
                onClick = {
                    isSettingsVisible = true
                }
            )

            Item(
                "Quit",
                onClick = ::exitApplication
            )
        }
    )
    /*
    if(isSettingsVisible) {
        Dialog(
            visible = isSettingsVisible,
            onCloseRequest = {
                isSettingsVisible = false
            },
            title = "ZenBreak settings",
        ) {
            appRoot.App()
        }
    }
     */

    Window(
        title = "",
        visible = isSettingsVisible,
        onCloseRequest = {
            isSettingsVisible = false
        },
        icon = null
    ) {
        appRoot.App()
    }
}

object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}