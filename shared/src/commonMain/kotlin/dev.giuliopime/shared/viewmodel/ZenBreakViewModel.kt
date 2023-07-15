package dev.giuliopime.shared.viewmodel

import dev.giuliopime.shared.data.model.ZbSettings
import dev.giuliopime.shared.data.model.ZbTimeData
import dev.giuliopime.shared.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import dev.giuliopime.shared.logic.IBreakManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Initializing the break action is **needed** with [setBreakAction]
 */
class ZenBreakViewModel: KoinComponent {
    private val settingsRepository: SettingsRepository by inject()
    private val breakManager: IBreakManager by inject()

    val zbSettings: Flow<ZbSettings> = settingsRepository.getSettingsFlow()

    fun setBreakAction(breakAction: (ZbSettings) -> Unit) {
        breakManager.setBreakAction(breakAction)
    }

    fun planBreak() = breakManager.planBreak()

    fun cancelBreak() = breakManager.cancelBreak()

    fun setHasCompletedFirstRun(completed: Boolean) = settingsRepository.setHasCompletedFirstRun(completed)

    fun setEnabled(enabled: Boolean) {
        settingsRepository.setEnabled(enabled)
        breakManager.planBreak()
    }

    fun setBreakFrequency(frequency: ZbTimeData) {
        settingsRepository.setBreakFrequency(frequency)
        breakManager.planBreak()
    }

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