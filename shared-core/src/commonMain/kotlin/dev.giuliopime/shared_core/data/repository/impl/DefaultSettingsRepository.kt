package dev.giuliopime.shared_core.data.repository.impl

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.data.model.ZbTimeData
import dev.giuliopime.shared_core.data.repository.ISettingsRepository
import dev.giuliopime.shared_core.data.source.SettingsStorage
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultSettingsRepository : ISettingsRepository, KoinComponent {
    private val settingsStorage: SettingsStorage by inject()

    override fun getSettings() : ZbSettings = settingsStorage.getZbSettings()

    @NativeCoroutines
    override fun getSettingsFlow() : Flow<ZbSettings> = settingsStorage.getZbSettingsFlow()

    override fun setHasCompletedFirstRun(completed: Boolean) = settingsStorage.setHasCompletedFirstRun(completed)

    override fun setEnabled(enabled: Boolean) = settingsStorage.setEnabled(enabled)

    override fun setBreakFrequency(frequency: ZbTimeData) = settingsStorage.setBreakFrequency(frequency)

    override fun setBreakDuration(duration: ZbTimeData) = settingsStorage.setBreakDuration(duration)

    override fun setBreakSkip(skip: Boolean) = settingsStorage.setBreakSkip(skip)

    override fun setBreakSnooze(snooze: Boolean) = settingsStorage.setBreakSnooze(snooze)

    override fun setBreakSnoozeLength(snoozeLength: ZbTimeData) = settingsStorage.setBreakSnoozeLength(snoozeLength)

    override fun setPopupNotification(popupNotification: Boolean) = settingsStorage.setPopupNotification(popupNotification)

    override fun setBreakMessage(message: String) = settingsStorage.setBreakMessage(message)

    override fun setPrimaryColor(primary: String) = settingsStorage.setPrimaryColor(primary)

    override fun setTextColor(text: String) = settingsStorage.setTextColor(text)

    override fun setResetOnIdle(reset: Boolean) = settingsStorage.setResetOnIdle(reset)

    override fun setStartAtLogin(start: Boolean) = settingsStorage.setStartAtLogin(start)
}