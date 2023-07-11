package ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.ZbSettings
import data.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import logic.BreakManager
import ui.components.BooleanSetting
import ui.components.DoubleChoiceSetting
import ui.components.TimeInputSetting

@Composable
fun BehaviourPage(
    breakManager: BreakManager,
    settingsRepository: SettingsRepository,
    settings: ZbSettings
) {
    TimeInputSetting(
        time = settings.breakFrequency,
        onTimeChange = {
            settingsRepository.setBreakFrequency(it)
            breakManager.planBreak(settings)
        },
        name = "Break frequency"
    )

    Spacer(Modifier.height(16.dp))

    DoubleChoiceSetting(
        name = "Notification type",
        value = settings.popupNotification,
        onValueChange = {
            settingsRepository.setPopupNotification(it)
        },
        positiveName = "Popup",
        negativeName = "Notification"
    )

    Spacer(Modifier.height(16.dp))

    AnimatedVisibility(settings.popupNotification) {
        Column {
            TimeInputSetting(
                time = settings.breakDuration,
                onTimeChange = {
                    settingsRepository.setBreakDuration(it)
                },
                name = "Break duration"
            )

            Spacer(Modifier.height(16.dp))
        }
    }

    BooleanSetting(
        checked = settings.breakSkip,
        onCheckedChange = {
            settingsRepository.setBreakSkip(it)
        },
        name = "Allow to skip break"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = settings.breakSnooze,
        onCheckedChange = {
            settingsRepository.setBreakSnooze(it)
        },
        name = "Allow to snooze break"
    )

    Spacer(Modifier.height(8.dp))

    AnimatedVisibility(settings.breakSnooze) {
        TimeInputSetting(
            time = settings.breakSnoozeLength,
            onTimeChange = {
                settingsRepository.setBreakSnoozeLength(it)
            },
            name = "Snooze length"
        )
    }
}