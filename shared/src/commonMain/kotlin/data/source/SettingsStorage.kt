package data.source

import data.model.ZbTimeData

interface SettingsStorage {
    fun getHasCompletedFirstRun(): Boolean

    fun getEnabled(): Boolean

    fun getBreakFrequency(): ZbTimeData

    fun getBreakDuration(): ZbTimeData

    fun getBreakSkip(): Boolean

    fun getBreakSnooze(): Boolean

    fun getBreakSnoozeLength(): ZbTimeData

    fun getPopupNotification(): Boolean

    fun getBreakMessage(): String

    fun getPrimaryColor(): String

    fun getSecondaryColor(): String

    fun getResetOnIdle(): Boolean

    fun getStartAtLogin(): Boolean


    fun setHasCompletedFirstRun(completed: Boolean)

    fun setEnabled(enabled: Boolean)

    fun setBreakFrequency(frequency: ZbTimeData)

    fun setBreakDuration(duration: ZbTimeData)

    fun setBreakSkip(skip: Boolean)

    fun setBreakSnooze(snooze: Boolean)

    fun setBreakSnoozeLength(snoozeLength: ZbTimeData)

    fun setPopupNotification(popupNotification: Boolean)

    fun setBreakMessage(message: String)

    fun setPrimaryColor(primary: String)

    fun setSecondaryColor(secondary: String)

    fun setResetOnIdle(reset: Boolean)

    fun setStartAtLogin(start: Boolean)
}