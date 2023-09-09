package dev.giuliopime.shared_compose_popup

import androidx.compose.ui.window.Window

fun BreakPopupView(
    message: String,
    duration: Long,
    onSkipClicked: () -> Unit,
    onSnoozeClicked: () -> Unit,
    onTimeFinished: () -> Unit,
    primaryColor: String,
    textColor: String
) = Window {
    ZbBreakPopup(
        message = message,
        duration = duration,
        onSkipClicked = onSkipClicked,
        onSnoozeClicked = onSnoozeClicked,
        onTimeFinished = onTimeFinished,
        primaryColor = primaryColor,
        textColor = textColor
    )
}