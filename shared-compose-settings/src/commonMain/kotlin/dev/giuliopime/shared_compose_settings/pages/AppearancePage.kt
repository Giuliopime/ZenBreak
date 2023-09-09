package dev.giuliopime.shared_compose_settings.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
import dev.giuliopime.shared_compose_settings.components.ColorSetting
import dev.giuliopime.shared_compose_settings.components.MultilineTextSetting

@Composable
fun AppearancePage(
    settings: ZbSettings,
    viewModel: IZenBreakViewModel
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