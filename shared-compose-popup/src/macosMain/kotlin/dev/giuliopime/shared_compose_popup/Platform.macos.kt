package dev.giuliopime.shared_compose_popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.5F)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
}