package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.model.TimeData

@Composable
fun TimeInputSetting(
    time: TimeData,
    onTimeChange: (TimeData) -> Unit,
    name: String
) {
    val hours = time.hours.toString().padStart(2, '0')
    val minutes = time.minutes.toString().padStart(2, '0')
    val seconds = time.seconds.toString().padStart(2, '0')

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
                fontWeight = FontWeight.Light
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = MaterialTheme.shapes.medium
            ).padding(4.dp)
        ) {
            TimeInputField(
                value = hours,
                onValueChange = {
                    TimeData.getHours(it)?.let { hours ->
                        onTimeChange(time.copy(hours = hours))
                    }
                }
            )
            Text(text = ":", textAlign = TextAlign.Center)
            TimeInputField(
                value = minutes,
                onValueChange = {
                    TimeData.getMinutes(it)?.let { minutes ->
                        onTimeChange(time.copy(minutes = minutes))
                    }
                }
            )
            Text(":")
            TimeInputField(
                value = seconds,
                onValueChange = {
                    TimeData.getSeconds(it)?.let { seconds ->
                        onTimeChange(time.copy(seconds = seconds))
                    }
                }
            )
        }
    }
}

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
        textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
        modifier = Modifier.width(40.dp),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}