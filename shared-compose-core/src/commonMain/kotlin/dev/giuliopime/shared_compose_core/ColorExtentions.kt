package dev.giuliopime.shared_compose_core

import androidx.compose.ui.graphics.Color

fun String.toColor(fallback: Color): Color {
    return if (Patterns.color.matches(this)) {
        Color(toColorInt())
    } else fallback
}

fun String.toColorInt(): Int {
    // Use a long to avoid rollovers on #ffXXXXXX
    var color: Long = substring(1).toLong(16)
    if (length == 7) {
        // Set the alpha value
        color = color or 0x00000000ff000000L
    } else if (length != 9) {
        throw IllegalArgumentException("Unknown color")
    }
    return color.toInt()
}

fun Color.contrastColor(): Color {
    // Calculate the perceptive luminance (aka luma) - human eye favors green color...
    val luma = (0.299 * red) + (0.587 * green) + (0.114 * blue)

    // Return black for bright colors, white for dark colors
    return if (luma > 0.6) {
        Color.Black
    } else {
        Color.White
    }
}