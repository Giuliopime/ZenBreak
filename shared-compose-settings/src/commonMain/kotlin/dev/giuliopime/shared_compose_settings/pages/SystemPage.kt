package dev.giuliopime.shared_compose_settings.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose_settings.FeatureFlags
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
import dev.giuliopime.shared_compose_settings.components.BooleanSetting

@Composable
fun SystemPage(
    settings: ZbSettings,
    viewModel: IZenBreakViewModel,
    featureFlags: FeatureFlags
) {
    BooleanSetting(
        checked = settings.resetOnIdle,
        onCheckedChange = {
            viewModel.setResetOnIdle(it)
        },
        name = "Reset on idle",
        enabled = featureFlags.resetOnIdle
    )

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = settings.startAtLogin,
        onCheckedChange = {
            viewModel.setStartAtLogin(it)
        },
        name = "Start at login",
        enabled = featureFlags.startAtLogin
    )
}