package dev.giuliopime.shared_compose.viewmodel

import dev.giuliopime.shared_compose.data.model.ZbSettings
import dev.giuliopime.shared_compose.data.model.ZbTimeData
import dev.giuliopime.shared_compose.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import dev.giuliopime.shared_compose.logic.BreakManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ZenBreakViewModel(
    private val breakManager: BreakManager
): KoinComponent {
    private val settingsRepository: SettingsRepository by inject()

    val zbSettings: Flow<ZbSettings> = settingsRepository.getSettings()

    fun setHasCompletedFirstRun(completed: Boolean) = settingsRepository.setHasCompletedFirstRun(completed)

    fun setEnabled(enabled: Boolean) = settingsRepository.setEnabled(enabled)

    fun setBreakFrequency(frequency: ZbTimeData) = settingsRepository.setBreakFrequency(frequency)

    fun setBreakDuration(duration: ZbTimeData) = settingsRepository.setBreakDuration(duration)

    fun setBreakSkip(skip: Boolean) = settingsRepository.setBreakSkip(skip)

    fun setBreakSnooze(snooze: Boolean) = settingsRepository.setBreakSnooze(snooze)

    fun setBreakSnoozeLength(snoozeLength: ZbTimeData) = settingsRepository.setBreakSnoozeLength(snoozeLength)

    fun setPopupNotification(popupNotification: Boolean) = settingsRepository.setPopupNotification(popupNotification)

    fun setBreakMessage(message: String) = settingsRepository.setBreakMessage(message)

    fun setPrimaryColor(primary: String) = settingsRepository.setPrimaryColor(primary)

    fun setTextColor(text: String) = settingsRepository.setTextColor(text)

    fun setResetOnIdle(reset: Boolean) = settingsRepository.setResetOnIdle(reset)

    fun setStartAtLogin(start: Boolean) = settingsRepository.setStartAtLogin(start)
}