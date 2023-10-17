package dev.giuliopime.shared_compose_settings.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose_settings.FeatureFlags
import dev.giuliopime.shared_compose_settings.components.ColorSetting
import dev.giuliopime.shared_compose_settings.components.DoubleChoiceSetting
import dev.giuliopime.shared_compose_settings.components.MultilineTextSetting
import dev.giuliopime.shared_compose_settings.dialogs.defaultColorPickerColors
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel

@Composable
fun AppearancePage(
    settings: ZbSettings,
    viewModel: IZenBreakViewModel,
    featureFlags: FeatureFlags
) {
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
                },
                colors = defaultColorPickerColors.toMutableList().apply { addAll(0, listOf("#000000", "#FFFFFF")) }
            )

            Spacer(Modifier.height(16.dp))
        }
    }

    MultilineTextSetting(
        name = "Break message",
        value = settings.breakMessage,
        onValueChange = {
            viewModel.setBreakMessage(it)
        }
    )
}