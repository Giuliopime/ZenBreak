package dev.giuliopime.shared.data.model

import kotlinx.serialization.Serializable

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