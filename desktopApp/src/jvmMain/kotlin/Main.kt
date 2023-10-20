import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import androidx.compose.ui.window.rememberWindowState
import dev.giuliopime.shared_compose_popup.ZbBreakPopup
import dev.giuliopime.shared_compose_settings.FeatureFlags
import dev.giuliopime.shared_compose_settings.ZenBreakUi
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.di.initKoin
import dev.giuliopime.shared_core.logic.IBreakManager
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
/*
import dorkbox.systemTray.MenuItem
import dorkbox.systemTray.Separator
import dorkbox.systemTray.SystemTray
 */
import kotlinx.coroutines.delay

private val featureFlags = FeatureFlags(
    startAtLogin = false,
    resetOnIdle = false
)
private val koin = initKoin().koin

fun main() = application {
    val viewModel: IZenBreakViewModel by koin.inject()
    val breakManager: IBreakManager by koin.inject()

    // val tray by remember { mutableStateOf(SystemTray.get()!!) }
    val trayState = rememberTrayState()

    var isPopupWindowVisible by remember { mutableStateOf(false) }
    val popupWindowState = rememberWindowState(placement = WindowPlacement.Maximized)

    val settings by viewModel.getSettings().collectAsState(ZbSettings())
    val settingsWindowState = rememberWindowState(
        size = DpSize(width = 400.dp, height = 600.dp)
    )
    var isSettingsWindowVisible by remember {
        mutableStateOf(false)
    }

    /*
    LaunchedEffect(Unit) {
        tray.setTooltip("ZenBreak")
        tray.setImage(javaClass.getResourceAsStream("/icon.png")) // Use icon in src/main/resources/icon.png
        tray.menu.apply {
            setCallback {
                isSettingsWindowVisible = true
            }

            add(MenuItem(if (settings.enabled) "Disable" else "Enable").apply {
                setCallback {
                    viewModel.setEnabled(!settings.enabled)
                }
            })

            add(MenuItem("Start break now").apply {
                setCallback {
                    breakManager.startBreak()
                }
            })

            add(MenuItem("Reset break").apply {
                setCallback {
                    isPopupWindowVisible = false
                    breakManager.planBreak()
                }
            })

            add(Separator())

            add(MenuItem("Settings").apply {
                setCallback {
                    isSettingsWindowVisible = true
                }
            })

            add(MenuItem("Quit").apply {
                setCallback {
                    exitApplication()
                }
            })
        }
    }
     */

    LaunchedEffect(Unit) {
        delay(2000)

        if (!settings.hasCompletedFirstRun) {
            isSettingsWindowVisible = true
            viewModel.setHasCompletedFirstRun(true)
        }

        breakManager.planBreak(false)
    }

    breakManager.setAction {
        if (it.popupNotification) {
            isPopupWindowVisible = true
        } else {
            trayState.sendNotification(
                Notification(
                    title = "ZenBreak",
                    message = it.breakMessage
                )
            )
        }
    }

    breakManager.setEndedAction {
        if (!it.popupNotification) {
            trayState.sendNotification(
                Notification(
                    title = "Break ended",
                    message = "The break has finished!"
                )
            )
        }

        breakManager.planBreak()
    }

    Tray(
        state = trayState,
        icon = painterResource("logo_tray.png"),
        onAction = {
            isSettingsWindowVisible = true
        },
        menu = {
            Item(
                text = if (settings.enabled) "Disable" else "Enable",
                onClick = {
                    viewModel.setEnabled(!settings.enabled)
                }
            )

            Item(
                "Start break now",
                onClick = {
                    breakManager.startBreak()
                }
            )

            Item(
                "Reset break",
                onClick = {
                    isPopupWindowVisible = false
                    breakManager.planBreak()
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
        state = settingsWindowState,
        visible = isSettingsWindowVisible,
        onCloseRequest = {
            isSettingsWindowVisible = false
        },
        icon = painterResource("logo.png")
    ) {
        ZenBreakUi(viewModel, featureFlags)
    }

    // Composables don't get disposed when the window is not visible anymore
    var showCounter by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(isPopupWindowVisible) {
        if (isPopupWindowVisible)
            showCounter++
    }

    Window(
        visible = isPopupWindowVisible,
        onCloseRequest = {
            isPopupWindowVisible = false
            breakManager.planBreak()
        },
        undecorated = true,
        resizable = false,
        transparent = true,
        alwaysOnTop = true,
        focusable = false,
        state = popupWindowState
    ) {
        window.toFront()
        window.requestFocus()

        AnimatedVisibility(
            visible = isPopupWindowVisible,
            enter = fadeIn(
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            ),
            exit = fadeOut()
        ) {
            key(showCounter) {
                Column(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.5F)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ZbBreakPopup(
                        message = settings.breakMessage,
                        duration = settings.breakDuration,
                        onSkipClicked = {
                            isPopupWindowVisible = false
                            breakManager.planBreak()
                        },
                        onSnoozeClicked = {
                            isPopupWindowVisible = false
                            breakManager.snoozeBreak()
                        },
                        onTimeFinished = {
                            isPopupWindowVisible = false
                        },
                        primaryColor = settings.primaryColor,
                        textColor = settings.textColor,
                        allowSkip = settings.breakSkip,
                        allowSnooze = settings.breakSnooze
                    )
                }
            }
        }
    }
}