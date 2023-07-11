package ui.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.ZbSettings
import data.repository.SettingsRepository
import ui.components.BooleanSetting

@Composable
fun SystemPage(
    settingsRepository: SettingsRepository,
    settings: ZbSettings
) {
    BooleanSetting(
        checked = settings.resetOnIdle,
        onCheckedChange = {
            settingsRepository.setResetOnIdle(it)
        },
        name = "Reset on idle"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = settings.startAtLogin,
        onCheckedChange = {
            settingsRepository.setStartAtLogin(it)
        },
        name = "Start at login"
    )
}