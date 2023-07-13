package dev.giuliopime.shared_compose.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun DoubleChoiceSetting(
    name: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    positiveName: String,
    negativeName: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        Text(name)

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = value,
                    onClick = {
                        onValueChange(true)
                    }
                )

                Text(positiveName)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = !value,
                    onClick = {
                        onValueChange(false)
                    }
                )

                Text(negativeName)
            }
        }
    }
}