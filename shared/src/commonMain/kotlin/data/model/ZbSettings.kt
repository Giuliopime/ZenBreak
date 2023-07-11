package data.model

data class ZbSettings(
    val hasCompletedFirstRun: Boolean = false,
    val enabled: Boolean = false,
    val breakFrequency: ZbTimeData = ZbTimeData(0, 20, 0),
    val breakDuration: ZbTimeData = ZbTimeData(0, 3, 0),
    val breakSkip: Boolean = true,
    val breakSnooze: Boolean = false,
    val breakSnoozeLength: ZbTimeData = ZbTimeData(0, 5, 0),
    val popupNotification: Boolean = true,
    val breakMessage: String = "It's time for a break!",
    val primaryColor: String = "#FFFFFF",
    val secondaryColor: String = "#000000",
    val resetOnIdle: Boolean = false,
    val startAtLogin: Boolean = false
)