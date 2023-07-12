package dev.giuliopime.shared_compose.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose.data.model.ZbSettings
import dev.giuliopime.shared_compose.data.repository.SettingsRepository
import ui.components.ColorSetting
import ui.components.MultilineTextSetting

@Composable
fun AppearancePage(
    settingsRepository: SettingsRepository,
    settings: ZbSettings
) {
    MultilineTextSetting(
        name = "Break message",
        value = settings.breakMessage,
        onValueChange = {
            settingsRepository.setBreakMessage(it)
        }
    )

    Spacer(Modifier.height(16.dp))

    ColorSetting(
        name = "Primary color",
        color = settings.primaryColor,
        onColorChange = {
            settingsRepository.setPrimaryColor(it)
        }
    )

    Spacer(Modifier.height(8.dp))

    ColorSetting(
        name = "Secondary color",
        color = settings.textColor,
        onColorChange = {
            settingsRepository.setSecondaryColor(it)
        }
    )
}