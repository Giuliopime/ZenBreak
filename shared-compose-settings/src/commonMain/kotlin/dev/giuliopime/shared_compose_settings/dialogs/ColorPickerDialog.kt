package dev.giuliopime.shared_compose_settings.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose_core.Patterns
import dev.giuliopime.shared_compose_core.contrastColor
import dev.giuliopime.shared_compose_core.toColor
import dev.giuliopime.shared_compose_core.toColorInt

val defaultColorPickerColors = listOf("#87CEEB", "#FFD1DC", "#C1D9B0", "#E6E6FA", "#FFFF99", "#A9A9A9", "#FFDAB9", "#BBCED1", "#F5F5DC", "#E0B0FF")

/**
 * Color strings should be formatted as hex color values (#000000)
 *
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerDialog(
    initialColor: String = "#87CEEB",
    colors: List<String> = defaultColorPickerColors,
    onChoice: (String) -> Unit
) {
    var color by remember(initialColor) { mutableStateOf(initialColor) }
    var hexTextColor by remember(initialColor) {
        mutableStateOf(initialColor.toColor(Color.White).contrastColor())
    }

    val colorRendered by animateColorAsState(
        targetValue = color.toColor(Color.White),
        label = "color-picker-animation",
        finishedListener = {
            hexTextColor = it.contrastColor()
        }
    )

    val onDismissRequest = {
        if (Patterns.color.matches(color)) {
            onChoice(color)
        }
    }

    AlertDialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            shape = AlertDialogDefaults.shape,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                BasicTextField(
                    value = color,
                    onValueChange = {
                        color = it
                    },
                    textStyle = MaterialTheme.typography.headlineSmall
                        .copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = hexTextColor
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorRendered)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 50.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(colors) {
                            Button(
                                onClick = { color = it },
                                shape = CircleShape,
                                modifier = Modifier.requiredSize(50.dp),
                                contentPadding = PaddingValues(1.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(it.toColorInt())
                                ),
                                border = if (it == color)
                                    BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface)
                                else
                                    null,
                                content = {}
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    TextButton(onClick = onDismissRequest, modifier = Modifier.align(Alignment.End)) {
                        Text(text = "Confirm", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}