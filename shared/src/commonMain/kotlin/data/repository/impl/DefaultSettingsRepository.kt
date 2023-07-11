package data.repository.impl

import data.model.ZbSettings
import data.model.ZbTimeData
import data.repository.SettingsRepository
import data.source.SettingsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultSettingsRepository(
    private val settingsStorage: SettingsStorage
) : SettingsRepository {

    private val zbSettings: MutableStateFlow<ZbSettings> = MutableStateFlow(loadSettings())

    private fun loadSettings() : ZbSettings {
        return ZbSettings(
            hasCompletedFirstRun = settingsStorage.getHasCompletedFirstRun(),
            enabled = settingsStorage.getEnabled(),
            breakFrequency = settingsStorage.getBreakFrequency(),
            breakDuration = settingsStorage.getBreakDuration(),
            breakSkip = settingsStorage.getBreakSkip(),
            breakSnooze = settingsStorage.getBreakSnooze(),
            breakSnoozeLength = settingsStorage.getBreakSnoozeLength(),
            popupNotification = settingsStorage.getPopupNotification(),
            breakMessage = settingsStorage.getBreakMessage(),
            primaryColor = settingsStorage.getPrimaryColor(),
            secondaryColor = settingsStorage.getSecondaryColor(),
            resetOnIdle = settingsStorage.getResetOnIdle(),
            startAtLogin = settingsStorage.getStartAtLogin()
        )
    }

    override fun getSettings() : Flow<ZbSettings> = zbSettings

    override fun setHasCompletedFirstRun(completed: Boolean) {
        settingsStorage.setHasCompletedFirstRun(completed)
        zbSettings.value = zbSettings.value.copy(
            hasCompletedFirstRun = completed
        )
    }

    override fun setEnabled(enabled: Boolean) {
        settingsStorage.setEnabled(enabled)
        zbSettings.value = zbSettings.value.copy(
            enabled = enabled
        )
    }

    override fun setBreakFrequency(frequency: ZbTimeData) {
        settingsStorage.setBreakFrequency(frequency)
        zbSettings.value = zbSettings.value.copy(
            breakFrequency = frequency
        )
    }

    override fun setBreakDuration(duration: ZbTimeData) {
        settingsStorage.setBreakDuration(duration)
        zbSettings.value = zbSettings.value.copy(
            breakDuration = duration
        )
    }

    override fun setBreakSkip(skip: Boolean) {
        settingsStorage.setBreakSkip(skip)
        zbSettings.value = zbSettings.value.copy(
            breakSkip = skip
        )
    }

    override fun setBreakSnooze(snooze: Boolean) {
        settingsStorage.setBreakSnooze(snooze)
        zbSettings.value = zbSettings.value.copy(
            breakSnooze = snooze
        )
    }

    override fun setBreakSnoozeLength(snoozeLength: ZbTimeData) {
        settingsStorage.setBreakSnoozeLength(snoozeLength)
        zbSettings.value = zbSettings.value.copy(
            breakSnoozeLength = snoozeLength
        )
    }

    override fun setPopupNotification(popupNotification: Boolean) {
        settingsStorage.setPopupNotification(popupNotification)
        zbSettings.value = zbSettings.value.copy(
            popupNotification = popupNotification
        )
    }

    override fun setBreakMessage(message: String) {
        settingsStorage.setBreakMessage(message)
        zbSettings.value = zbSettings.value.copy(
            breakMessage = message
        )
    }

    override fun setPrimaryColor(primary: String) {
        settingsStorage.setPrimaryColor(primary)
        zbSettings.value = zbSettings.value.copy(
            primaryColor = primary
        )
    }

    override fun setSecondaryColor(secondary: String) {
        settingsStorage.setSecondaryColor(secondary)
        zbSettings.value = zbSettings.value.copy(
            secondaryColor = secondary
        )
    }

    override fun setResetOnIdle(reset: Boolean) {
        settingsStorage.setResetOnIdle(reset)
        zbSettings.value = zbSettings.value.copy(
            resetOnIdle = reset
        )
    }

    override fun setStartAtLogin(start: Boolean) {
        settingsStorage.setStartAtLogin(start)
        zbSettings.value = zbSettings.value.copy(
            startAtLogin = start
        )
    }
}