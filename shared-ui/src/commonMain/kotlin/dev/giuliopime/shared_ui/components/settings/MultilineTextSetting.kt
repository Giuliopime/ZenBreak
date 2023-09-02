package dev.giuliopime.shared_ui.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MultilineTextSetting(
    name: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        Text(name)

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = false,
            maxLines = 10
        )
    }
}