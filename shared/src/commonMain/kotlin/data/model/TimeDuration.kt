package data.model

import kotlin.time.Duration.Companion.hours

data class TimeDuration(
    val hours: Int,
    val minutes: Int,
    val seconds: Int
) {
    companion object {
        fun getHours(hours: String) = hours.toIntOrNull()?.coerceIn(0, 23)

        fun getMinutes(minutes: String) = minutes.toIntOrNull()?.coerceIn(0, 59)

        fun getSeconds(seconds: String) = seconds.toIntOrNull()?.coerceIn(0, 59)
    }
}