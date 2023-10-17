package dev.giuliopime.shared_compose_settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose_core.toColor
import dev.giuliopime.shared_compose_settings.dialogs.ColorPickerDialog
import dev.giuliopime.shared_compose_settings.dialogs.defaultColorPickerColors

@Composable
fun ColorSetting(
    name: String,
    color: String,
    onColorChange: (String) -> Unit,
    colors: List<String> = defaultColorPickerColors
) {
    var showPicker by remember { mutableStateOf(false) }

    if (showPicker) {
        ColorPickerDialog(
            initialColor = color,
            onChoice = {
                showPicker = false
                onColorChange(it)
            },
            colors = colors
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(name)

        Box(Modifier
            .size(width = 48.dp, height = 32.dp)
            .clip(MaterialTheme.shapes.small)
            .background(color.toColor(Color.Cyan))
            .border(border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.outline), shape = MaterialTheme.shapes.small)
            .clickable {
                showPicker = !showPicker
            }
        )
    }
}