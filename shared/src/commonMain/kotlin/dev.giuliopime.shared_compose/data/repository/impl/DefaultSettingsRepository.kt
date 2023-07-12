package dev.giuliopime.shared_compose.data.repository.impl

import dev.giuliopime.shared_compose.data.model.ZbSettings
import dev.giuliopime.shared_compose.data.model.ZbTimeData
import dev.giuliopime.shared_compose.data.repository.SettingsRepository
import dev.giuliopime.shared_compose.data.source.SettingsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultSettingsRepository : SettingsRepository, KoinComponent {
    private val settingsStorage: SettingsStorage by inject()

    override fun getSettings() : ZbSettings = settingsStorage.getZbSettings()

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