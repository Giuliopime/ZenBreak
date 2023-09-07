package dev.giuliopime.shared_core.data.source

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.giuliopime.shared_core.data.model.ZbSettings
import kotlinx.coroutines.flow.Flow

interface SettingsStorage {
    @NativeCoroutines
    fun getZbSettingsFlow(): Flow<ZbSettings>

    fun getZbSettings(): ZbSettings

    fun setHasCompletedFirstRun(completed: Boolean)

    fun setEnabled(enabled: Boolean)

    fun setBreakFrequency(frequency: Long)

    fun setBreakDuration(duration: Long)

    fun setBreakSkip(skip: Boolean)

    fun setBreakSnooze(snooze: Boolean)

    fun setBreakSnoozeLength(snoozeLength: Long)

    fun setPopupNotification(popupNotification: Boolean)

    fun setBreakMessage(message: String)

    fun setPrimaryColor(primary: String)

    fun setTextColor(text: String)

    fun setResetOnIdle(reset: Boolean)

    fun setStartAtLogin(start: Boolean)
}