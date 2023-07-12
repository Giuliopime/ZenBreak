package dev.giuliopime.shared_compose.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared.data.model.ZbSettings
import dev.giuliopime.shared.data.repository.SettingsRepository
import dev.giuliopime.shared.viewmodel.ZenBreakViewModel
import dev.giuliopime.shared_compose.components.ColorSetting
import dev.giuliopime.shared_compose.components.MultilineTextSetting

@Composable
fun AppearancePage(
    viewModel: ZenBreakViewModel,
    settings: ZbSettings
) {
    MultilineTextSetting(
        name = "Break message",
        value = settings.breakMessage,
        onValueChange = {
            viewModel.setBreakMessage(it)
        }
    )

    Spacer(Modifier.height(16.dp))

    ColorSetting(
        name = "Primary color",
        color = settings.primaryColor,
        onColorChange = {
            viewModel.setPrimaryColor(it)
        }
    )

    Spacer(Modifier.height(8.dp))

    ColorSetting(
        name = "Text color",
        color = settings.textColor,
        onColorChange = {
            viewModel.setTextColor(it)
        }
    )
}