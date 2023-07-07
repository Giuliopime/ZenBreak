package ui.pages

import SettingsRepository
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.components.ColorSetting
import ui.components.DoubleChoiceSetting
import ui.components.MultilineTextSetting

@Composable
fun AppearancePage(
    settingsRepository: SettingsRepository
) {
    var popupNotification by remember { mutableStateOf(true) }
    var breakMessage by remember { mutableStateOf("Take a break!") }
    var primaryColor by remember { mutableStateOf("#FFFFFF") }
    var secondaryColor by remember { mutableStateOf("#000000") }

    DoubleChoiceSetting(
        name = "Notification type",
        value = popupNotification,
        onValueChange = {
            popupNotification = it
        },
        positiveName = "Popup",
        negativeName = "Notification"
    )

    Spacer(Modifier.height(16.dp))

    MultilineTextSetting(
        name = "Break message",
        value = breakMessage,
        onValueChange = {
            breakMessage = it
        }
    )

    Spacer(Modifier.height(16.dp))

    ColorSetting(
        name = "Primary color",
        color = primaryColor,
        onColorChange = {
            primaryColor = it
        }
    )

    Spacer(Modifier.height(8.dp))

    ColorSetting(
        name = "Secondary color",
        color = secondaryColor,
        onColorChange = {
            secondaryColor = it
        }
    )
}