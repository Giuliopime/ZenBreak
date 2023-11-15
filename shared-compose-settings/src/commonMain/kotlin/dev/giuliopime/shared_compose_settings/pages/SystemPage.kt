package dev.giuliopime.shared_compose_settings.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose_settings.FeatureFlags
import dev.giuliopime.shared_compose_settings.components.BooleanSetting
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel

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

    AnimatedVisibility(
        visible = !featureFlags.resetOnIdle,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.errorContainer, MaterialTheme.shapes.medium)
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(
            text = "This feature is currently not supported for your operating system!",
            color = MaterialTheme.colorScheme.onErrorContainer
        )
    }

    Spacer(Modifier.height(16.dp))

    BooleanSetting(
        checked = settings.startAtLogin,
        onCheckedChange = {
            viewModel.setStartAtLogin(it)
        },
        name = "Start at login",
        enabled = featureFlags.startAtLogin
    )

    AnimatedVisibility(
        visible = !featureFlags.startAtLogin,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.errorContainer, MaterialTheme.shapes.medium)
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(
            text = "This feature is currently not supported for your operating system!",
            color = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}