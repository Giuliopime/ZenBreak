package ui.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.repository.SettingsRepository
import ui.components.BooleanSetting

@Composable
fun SystemPage(
    settingsRepository: SettingsRepository
) {
    val resetOnIdle by settingsRepository.resetOnIdle
    val startAtLogin by settingsRepository.startAtLogin

    BooleanSetting(
        checked = resetOnIdle,
        onCheckedChange = {
            settingsRepository.saveResetOnIdle(it)
        },
        name = "Reset on idle"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = startAtLogin,
        onCheckedChange = {
            settingsRepository.saveStartAtLogin(it)
        },
        name = "Start at login"
    )
}