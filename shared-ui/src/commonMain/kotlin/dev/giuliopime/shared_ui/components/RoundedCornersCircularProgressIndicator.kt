@file:Suppress("UNUSED")

package dev.giuliopime.shared_ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCornersCircularProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.circularColor,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth
) {
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
    }
    Canvas(
        modifier
            .progressSemantics(progress)
            .size(CircularIndicatorDiameter)
    ) {
        // Start at 12 o'clock
        val startAngle = 270f
        val sweep = progress * 360f
        drawDeterminateCircularIndicator(startAngle, sweep, color, stroke)
    }
}

internal enum class ColorSchemeKeyTokens {
    Background,
    Error,
    ErrorContainer,
    InverseOnSurface,
    InversePrimary,
    InverseSurface,
    OnBackground,
    OnError,
    OnErrorContainer,
    OnPrimary,
    OnPrimaryContainer,
    OnSecondary,
    OnSecondaryContainer,
    OnSurface,
    OnSurfaceVariant,
    OnTertiary,
    OnTertiaryContainer,
    Outline,
    OutlineVariant,
    Primary,
    PrimaryContainer,
    Scrim,
    Secondary,
    SecondaryContainer,
    Surface,
    SurfaceTint,
    SurfaceVariant,
    Tertiary,
    TertiaryContainer,
}

internal enum class ShapeKeyTokens {
    CornerExtraLarge,
    CornerExtraLargeTop,
    CornerExtraSmall,
    CornerExtraSmallTop,
    CornerFull,
    CornerLarge,
    CornerLargeEnd,
    CornerLargeTop,
    CornerMedium,
    CornerNone,
    CornerSmall,
}

internal object CircularProgressIndicatorTokens {
    val ActiveIndicatorColor = ColorSchemeKeyTokens.Primary
    val ActiveShape = ShapeKeyTokens.CornerNone
    val ActiveIndicatorWidth = 4.0.dp
    val FourColorActiveIndicatorFourColor = ColorSchemeKeyTokens.TertiaryContainer
    val FourColorActiveIndicatorOneColor = ColorSchemeKeyTokens.Primary
    val FourColorActiveIndicatorThreeColor = ColorSchemeKeyTokens.Tertiary
    val FourColorActiveIndicatorTwoColor = ColorSchemeKeyTokens.PrimaryContainer
    val Size = 48.0.dp
}

internal val CircularIndicatorDiameter =
    CircularProgressIndicatorTokens.Size - CircularProgressIndicatorTokens.ActiveIndicatorWidth * 2

private fun DrawScope.drawDeterminateCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) = drawCircularIndicator(startAngle, sweep, color, stroke)

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
    // To do this we need to remove half the stroke width from the total diameter for both sides.
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}