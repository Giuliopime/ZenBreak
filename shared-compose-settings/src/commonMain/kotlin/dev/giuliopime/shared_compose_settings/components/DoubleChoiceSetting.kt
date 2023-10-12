package dev.giuliopime.shared_compose_settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties

@Composable
fun DoubleChoiceSetting(
    name: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    positiveName: String,
    negativeName: String
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        Text(name)

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(positiveName)
                },
                onClick = {
                    expanded = false
                    onValueChange(true)
                }
            )

            DropdownMenuItem(
                text = {
                    Text(negativeName)
                },
                onClick = {
                    expanded = false
                    onValueChange(true)
                }
            )
        }
    }
}