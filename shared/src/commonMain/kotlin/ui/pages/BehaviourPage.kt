package ui.pages

import SettingsRepository
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.TimeDuration
import ui.components.BooleanSetting
import ui.components.TimeInputSetting

@Composable
fun BehaviourPage(
    settingsRepository: SettingsRepository
) {
    var breakFrequency by remember { mutableStateOf(TimeDuration(0, 0, 0)) }
    var breakDuration by remember { mutableStateOf(TimeDuration(0, 0, 0)) }
    var breakSkip by remember { mutableStateOf(false) }
    var breakSnooze by remember { mutableStateOf(false) }
    var breakSnoozeDuration by remember { mutableStateOf(TimeDuration(0, 0, 0)) }

    TimeInputSetting(
        time = breakFrequency,
        onTimeChange = {
            breakFrequency = it
        },
        name = "Break frequency"
    )

    Spacer(Modifier.height(16.dp))

    TimeInputSetting(
        time = breakDuration,
        onTimeChange = {
            breakDuration = it
        },
        name = "Break duration"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = breakSkip,
        onCheckedChange = {
            breakSkip = it
        },
        name = "Allow to skip break"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = breakSnooze,
        onCheckedChange = {
            breakSnooze = it
        },
        name = "Allow to snooze break"
    )

    Spacer(Modifier.height(8.dp))

    AnimatedVisibility(breakSnooze) {
        TimeInputSetting(
            time = breakSnoozeDuration,
            onTimeChange = {
                breakSnoozeDuration = it
            },
            name = "Snooze length"
        )
    }
}