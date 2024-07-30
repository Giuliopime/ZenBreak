package dev.giuliopime.shared_compose_settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MinuteInputSetting(
    time: Long,
    onTimeChange: (Long) -> Unit,
    name: String
) {
    val minutes by remember(time) {
        if (time < 60000) {
            mutableStateOf(time / 1000)
        } else {
            mutableStateOf(time / 60000)
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(name)

            if (time < 60000) {
                Text("$minutes sec", style = MaterialTheme.typography.labelMedium)
            } else {
                Text("$minutes min", style = MaterialTheme.typography.labelMedium)
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 2.dp
            )
        ) {
            FilledIconButton(
                onClick = {
                    if (time < 60000) {
                        onTimeChange(time + 10000)
                    } else {
                        onTimeChange(time + 60000)
                    }
                },
                shape = RoundedCornerShape(
                    topStart = 6.dp,
                    topEnd = 6.dp
                ),
                modifier = Modifier.size(width = 36.dp, height = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Increment $name"
                )
            }

            FilledIconButton(
                onClick = {
                    if (time > 60000) {
                        onTimeChange(time - 60000)
                    } else {
                        onTimeChange(time - 10000)
                    }
                },
                shape = RoundedCornerShape(
                    bottomStart = 6.dp,
                    bottomEnd = 6.dp
                ),
                modifier = Modifier.size(width = 36.dp, height = 24.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowDown, "Decrement $name")
            }
        }
    }

    /*
    val customTextSelectionColors = TextSelectionColors(
        handleColor = MaterialTheme.colorScheme.onPrimary,
        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp
                ),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(name)
                Text(
                    text = "(hh:mm:ss)",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Light,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        shape = MaterialTheme.shapes.medium
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp)
            ) {
                TimeInputField(
                    value = hours.toString(),
                    onValueChange = {
                        /*
                        ZbTimeData.getHours(it)?.let { hours ->
                            onTimeChange(time.copy(hours = hours))
                        }

                         */

                    }
                )
                TimeInputSeparator()
                TimeInputField(
                    value = minutes.toString(),
                    onValueChange = {

                    }
                )
                TimeInputSeparator()
                TimeInputField(
                    value = seconds.toString(),
                    onValueChange = {

                    }
                )
            }
            }
            }
     */
}
/*
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TimeInputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var textFieldValue by remember(value) {
        mutableStateOf(
            TextFieldValue(text = value, selection = TextRange(value.length))
        )
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        textFieldValue = textFieldValue.copy(
            selection = if (isFocused) {
                TextRange(
                    start = 0,
                    end = textFieldValue.text.length
                )
            } else {
                TextRange.Zero
            }
        )

    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            if (it.text != textFieldValue.text)
                onValueChange(it.text)
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier.width(40.dp),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
    )
}

@Composable
fun TimeInputSeparator() {
    Text(text = ":", color = MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold)
}
 */
