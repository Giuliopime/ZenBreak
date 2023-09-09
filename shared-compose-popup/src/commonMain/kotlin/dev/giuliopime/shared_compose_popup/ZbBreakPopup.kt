package dev.giuliopime.shared_compose_popup

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose_core.theme.ZenBreakTheme
import dev.giuliopime.shared_compose_core.toColor

@Composable
fun ZbBreakPopup(
    message: String,
    duration: Long,
    onSkipClicked: () -> Unit,
    onSnoozeClicked: () -> Unit,
    onTimeFinished: () -> Unit,
    primaryColor: String,
    textColor: String
) {
    var targetProgress by remember {
        mutableStateOf(1F)
    }
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(
            durationMillis = duration.toInt(),
            easing = LinearEasing
        ),
        finishedListener = {
            onTimeFinished()
        }
    )

    LaunchedEffect(duration) {
        targetProgress = 0F
    }

    ZenBreakTheme {
        Box(
            modifier = Modifier
                .size(512.dp)
                .background(Color.Transparent)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(primaryColor.toColor(Color.White))
                    .padding(64.dp)
            ) {
                Text(
                    text = message,
                    color = textColor.toColor(Color.Black),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                Row {
                    Button(
                        onClick = onSkipClicked,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.inverseSurface,
                            contentColor = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    ) {
                        Text("Skip")
                    }

                    Spacer(Modifier.width(12.dp))

                    Button(
                        onClick = onSnoozeClicked,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.inverseSurface,
                            contentColor = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    ) {
                        Text("Snooze")
                    }
                }
            }

            RoundedCornersCircularProgressIndicator(
                progress = animatedProgress,
                color = textColor.toColor(Color.Black),
                strokeWidth = 8.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
    }
}