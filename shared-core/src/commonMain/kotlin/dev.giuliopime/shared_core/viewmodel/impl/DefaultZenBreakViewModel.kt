package dev.giuliopime.shared_core.viewmodel.impl

import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.data.repository.ISettingsRepository
import dev.giuliopime.shared_core.logic.IBreakManager
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultZenBreakViewModel: IZenBreakViewModel, KoinComponent {
    private val settingsRepository: ISettingsRepository by inject()
    private val breakManager: IBreakManager by inject()

    override fun getSettings(): Flow<ZbSettings> = settingsRepository.getSettingsFlow()

    override fun setHasCompletedFirstRun(completed: Boolean) = settingsRepository.setHasCompletedFirstRun(completed)

    override fun setEnabled(enabled: Boolean) {
        settingsRepository.setEnabled(enabled)
        breakManager.planBreak()
    }

    override fun setBreakFrequency(frequency: Long) {
        settingsRepository.setBreakFrequency(frequency)
        breakManager.planBreak()
    }

    override fun setBreakDuration(duration: Long) = settingsRepository.setBreakDuration(duration)

    override fun setBreakSkip(skip: Boolean) = settingsRepository.setBreakSkip(skip)

    override fun setBreakSnooze(snooze: Boolean) = settingsRepository.setBreakSnooze(snooze)

    override fun setBreakSnoozeDuration(snoozeDuration: Long) = settingsRepository.setBreakSnoozeDuration(snoozeDuration)

    override fun setPopupNotification(popupNotification: Boolean) = settingsRepository.setPopupNotification(popupNotification)

    override fun setBreakMessage(message: String) = settingsRepository.setBreakMessage(message)

    override fun setPrimaryColor(primary: String) = settingsRepository.setPrimaryColor(primary)

    override fun setTextColor(text: String) = settingsRepository.setTextColor(text)

    override fun setResetOnIdle(reset: Boolean) = settingsRepository.setResetOnIdle(reset)

    override fun setStartAtLogin(start: Boolean) = settingsRepository.setStartAtLogin(start)
}