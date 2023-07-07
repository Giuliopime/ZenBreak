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
import ui.components.BooleanSetting

@Composable
fun SystemPage() {
    var resetOnIdle by remember { mutableStateOf(false) }
    var startAtLogin by remember { mutableStateOf(false) }

    BooleanSetting(
        checked = resetOnIdle,
        onCheckedChange = {
            resetOnIdle = it
        },
        name = "Reset on idle"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = startAtLogin,
        onCheckedChange = {
            startAtLogin = it
        },
        name = "Start at login"
    )
}