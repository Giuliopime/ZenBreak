package ui.pages

import data.repository.SettingsRepository
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.components.ColorSetting
import ui.components.DoubleChoiceSetting
import ui.components.MultilineTextSetting

@Composable
fun AppearancePage(
    settingsRepository: SettingsRepository
) {
    val popupNotification by settingsRepository.popupNotification
    val breakMessage by settingsRepository.breakMessage
    val primaryColor by settingsRepository.primaryColor
    val secondaryColor by settingsRepository.secondaryColor

    DoubleChoiceSetting(
        name = "Notification type",
        value = popupNotification,
        onValueChange = {
            settingsRepository.savePopupNotification(it)
        },
        positiveName = "Popup",
        negativeName = "Notification"
    )

    Spacer(Modifier.height(16.dp))

    MultilineTextSetting(
        name = "Break message",
        value = breakMessage,
        onValueChange = {
            settingsRepository.saveBreakMessage(it)
        }
    )

    Spacer(Modifier.height(16.dp))

    ColorSetting(
        name = "Primary color",
        color = primaryColor,
        onColorChange = {
            settingsRepository.savePrimaryColor(it)
        }
    )

    Spacer(Modifier.height(8.dp))

    ColorSetting(
        name = "Secondary color",
        color = secondaryColor,
        onColorChange = {
            settingsRepository.saveSecondaryColor(it)
        }
    )
}