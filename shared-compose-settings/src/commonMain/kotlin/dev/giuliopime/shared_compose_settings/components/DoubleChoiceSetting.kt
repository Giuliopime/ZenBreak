package dev.giuliopime.shared_compose_settings.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

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
    val iconRotation by animateFloatAsState(if (expanded) 180F else 0F)

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        Text(name)

        Box {
            Row(
                modifier = Modifier
                    .width(124.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable {
                        expanded = !expanded
                    }
                    .padding(start = 6.dp, bottom = 2.dp, top = 2.dp, end = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(if (value) positiveName else negativeName)

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "toggle dropdown",
                    modifier = Modifier.rotate(iconRotation)
                )
            }

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
                        onValueChange(false)
                    }
                )
            }
        }
    }
}