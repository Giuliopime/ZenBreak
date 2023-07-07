package ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.repository.SettingsRepository
import ui.components.BooleanSetting
import ui.components.TimeInputSetting

@Composable
fun BehaviourPage(
    settingsRepository: SettingsRepository
) {
    val breakFrequency by settingsRepository.breakFrequency
    val breakDuration by settingsRepository.breakDuration
    val breakSkip by settingsRepository.breakSkip
    val breakSnooze by settingsRepository.breakSnooze
    val breakSnoozeDuration by settingsRepository.breakSnoozeLength

    TimeInputSetting(
        time = breakFrequency,
        onTimeChange = {
            settingsRepository.saveBreakFrequency(it)
        },
        name = "Break frequency"
    )

    Spacer(Modifier.height(16.dp))

    TimeInputSetting(
        time = breakDuration,
        onTimeChange = {
            settingsRepository.saveBreakDuration(it)
        },
        name = "Break duration"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = breakSkip,
        onCheckedChange = {
            settingsRepository.saveBreakSkip(it)
        },
        name = "Allow to skip break"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = breakSnooze,
        onCheckedChange = {
            settingsRepository.saveBreakSnooze(it)
        },
        name = "Allow to snooze break"
    )

    Spacer(Modifier.height(8.dp))

    AnimatedVisibility(breakSnooze) {
        TimeInputSetting(
            time = breakSnoozeDuration,
            onTimeChange = {
                settingsRepository.saveBreakSnoozeLength(it)
            },
            name = "Snooze length"
        )
    }
}