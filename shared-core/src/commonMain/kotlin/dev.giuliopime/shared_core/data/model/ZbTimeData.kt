package dev.giuliopime.shared_core.data.model

import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Contains values that define a fixed amount of time
 *
 * @param hours
 * @param minutes
 * @param seconds
 */
@Serializable
data class ZbTimeData(
    val hours: Int,
    val minutes: Int,
    val seconds: Int
) {
    /**
     * The time expressed in milliseconds (defaults to a minimum of 5000)
     *
     * See also [ZbTimeData.duration]
     */
    val millis = (hours.hours.inWholeMilliseconds +
            minutes.minutes.inWholeMilliseconds +
            seconds.seconds.inWholeMilliseconds).coerceAtLeast(5000)

    /**
     * [Duration] type for [ZbTimeData.millis]
     */
    val duration = millis.milliseconds

    companion object {
        val default = ZbTimeData(0, 20, 0)

        /**
         * Converts a string to an integer representing 0-23 hours
         *
         * @return hours or null
         */
        fun getHours(hours: String) = hours.toIntOrNull()?.coerceIn(0, 23)

        /**
         * Converts a string to an integer representing 0-59 minutes
         *
         * @return minutes or null
         */
        fun getMinutes(minutes: String) = minutes.toIntOrNull()?.coerceIn(0, 59)

        /**
         * Converts a string to an integer representing a 0-59 seconds
         *
         * @return seconds or null
         */
        fun getSeconds(seconds: String) = seconds.toIntOrNull()?.coerceIn(0, 59)
    }
}