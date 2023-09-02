package dev.giuliopime.shared_core.data.model

import kotlinx.serialization.Serializable

/**
 * ZenBreak Settings that gets stored on the device
 *
 * @param hasCompletedFirstRun whether the app has been ran at least once,
 * this could include for example having completed a welcome screen and so its meaning is implementation specific
 * @param enabled whether ZenBreak is enabled
 * @param breakFrequency time between two breaks
 * @param breakDuration duration of a break
 * @param breakSkip whether skipping breaks is allowed
 * @param breakSnooze whether snoozing breaks is allowed
 * @param breakSnoozeLength duration of a snooze
 * @param popupNotification whether the break appears as a popup or a simple notification
 * @param breakMessage the message of the break
 * @param primaryColor primary color for the break, format **must be #RRGGBB**
 * @param textColor color for the text of the break, format **must be #RRGGBB**
 * @param resetOnIdle if enabled, the break will canceled when the device goes idle, and restart when the device gets turned on
 * @param startAtLogin run on device startup
 */
@Serializable
data class ZbSettings(
    val hasCompletedFirstRun: Boolean = false,
    val enabled: Boolean = false,
    val breakFrequency: ZbTimeData = ZbTimeData(0, 20, 0),
    val breakDuration: ZbTimeData = ZbTimeData(0, 3, 0),
    val breakSkip: Boolean = true,
    val breakSnooze: Boolean = false,
    val breakSnoozeLength: ZbTimeData = ZbTimeData(0, 5, 0),
    val popupNotification: Boolean = true,
    val breakMessage: String = "Rest your eyes, and do some stretching ^^",
    val primaryColor: String = "#90e0ef",
    val textColor: String = "#000000",
    val resetOnIdle: Boolean = false,
    val startAtLogin: Boolean = false
)