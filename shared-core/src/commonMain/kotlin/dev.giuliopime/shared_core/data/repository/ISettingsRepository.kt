package dev.giuliopime.shared_core.data.repository

import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.data.model.ZbTimeData
import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    fun getSettings() : ZbSettings
    fun getSettingsFlow() : Flow<ZbSettings>

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

    fun setTextColor(text: String)

    fun setResetOnIdle(reset: Boolean)

    fun setStartAtLogin(start: Boolean)
}