package dev.giuliopime.shared_compose.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
import dev.giuliopime.shared_compose.components.settings.BooleanSetting
import dev.giuliopime.shared_compose.components.settings.DoubleChoiceSetting
import dev.giuliopime.shared_compose.components.settings.TimeInputSetting

@Composable
fun BehaviourPage(
    settings: ZbSettings,
    viewModel: IZenBreakViewModel,
) {
    TimeInputSetting(
        time = settings.breakFrequency,
        onTimeChange = {
            viewModel.setBreakFrequency(it)
        },
        name = "Break frequency"
    )

    Spacer(Modifier.height(16.dp))

    DoubleChoiceSetting(
        name = "Notification type",
        value = settings.popupNotification,
        onValueChange = {
            viewModel.setPopupNotification(it)
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
                    viewModel.setBreakDuration(it)
                },
                name = "Break duration"
            )

            Spacer(Modifier.height(16.dp))
        }
    }

    BooleanSetting(
        checked = settings.breakSkip,
        onCheckedChange = {
            viewModel.setBreakSkip(it)
        },
        name = "Allow to skip break"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = settings.breakSnooze,
        onCheckedChange = {
            viewModel.setBreakSnooze(it)
        },
        name = "Allow to snooze break"
    )

    Spacer(Modifier.height(8.dp))

    AnimatedVisibility(settings.breakSnooze) {
        TimeInputSetting(
            time = settings.breakSnoozeDuration,
            onTimeChange = {
                viewModel.setBreakSnoozeDuration(it)
            },
            name = "Snooze length"
        )
    }
}