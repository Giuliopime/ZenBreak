package dev.giuliopime.shared_ui.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
import dev.giuliopime.shared_ui.components.settings.BooleanSetting

@Composable
fun SystemPage(
    settings: ZbSettings,
    viewModel: IZenBreakViewModel
) {
    BooleanSetting(
        checked = settings.resetOnIdle,
        onCheckedChange = {
            viewModel.setResetOnIdle(it)
        },
        name = "Reset on idle"
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = settings.startAtLogin,
        onCheckedChange = {
            viewModel.setStartAtLogin(it)
        },
        name = "Start at login"
    )
}