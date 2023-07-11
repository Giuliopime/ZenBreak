package data.model

import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Serializable
data class ZbTimeData(
    val hours: Int,
    val minutes: Int,
    val seconds: Int
) {
    val millis = (hours.hours.inWholeMilliseconds +
            minutes.minutes.inWholeMilliseconds +
            seconds.seconds.inWholeMilliseconds).coerceAtLeast(5000)

    companion object {
        fun getHours(hours: String) = hours.toIntOrNull()?.coerceIn(0, 23)

        fun getMinutes(minutes: String) = minutes.toIntOrNull()?.coerceIn(0, 59)

        fun getSeconds(seconds: String) = seconds.toIntOrNull()?.coerceIn(0, 59)
    }
}