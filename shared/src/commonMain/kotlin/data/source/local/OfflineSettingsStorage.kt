package data.source.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.set
import data.model.ZbSettings
import data.model.ZbTimeData
import data.source.SettingsStorage
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
class OfflineSettingsStorage: SettingsStorage {
    private val settings = Settings()
    private val defaultValues = ZbSettings()

    override fun getHasCompletedFirstRun(): Boolean {
        return settings.getBoolean(OfflineStorageKeys.isFirstRun, defaultValues.hasCompletedFirstRun)
    }

    override fun getEnabled(): Boolean {
        return settings.getBoolean(OfflineStorageKeys.enabled, defaultValues.enabled)
    }

    override fun getBreakFrequency(): ZbTimeData {
        return settings.decodeValueOrNull(
            serializer = ZbTimeData.serializer(),
            key = OfflineStorageKeys.breakFrequency
        ) ?: defaultValues.breakFrequency
    }

    override fun getBreakDuration(): ZbTimeData {
        return settings.decodeValueOrNull(
            serializer = ZbTimeData.serializer(),
            key = OfflineStorageKeys.breakDuration
        ) ?: defaultValues.breakDuration
    }

    override fun getBreakSkip(): Boolean {
        return settings.getBoolean(OfflineStorageKeys.breakSkip, defaultValues.breakSkip)
    }

    override fun getBreakSnooze(): Boolean {
        return settings.getBoolean(OfflineStorageKeys.breakSnooze, defaultValues.breakSnooze)
    }

    override fun getBreakSnoozeLength(): ZbTimeData {
        return settings.decodeValueOrNull(
            serializer = ZbTimeData.serializer(),
            key = OfflineStorageKeys.breakSnoozeLength
        ) ?: defaultValues.breakSnoozeLength
    }

    override fun getPopupNotification(): Boolean {
        return settings.getBoolean(OfflineStorageKeys.popupNotification, defaultValues.popupNotification)
    }

    override fun getBreakMessage(): String {
        return settings.getString(OfflineStorageKeys.breakMessage, defaultValues.breakMessage)
    }

    override fun getPrimaryColor(): String {
        return settings.getString(OfflineStorageKeys.primaryColor, defaultValues.primaryColor)
    }

    override fun getSecondaryColor(): String {
        return settings.getString(OfflineStorageKeys.secondaryColor, defaultValues.secondaryColor)
    }

    override fun getResetOnIdle(): Boolean {
        return settings.getBoolean(OfflineStorageKeys.resetOnIdle, defaultValues.resetOnIdle)
    }

    override fun getStartAtLogin(): Boolean {
        return settings.getBoolean(OfflineStorageKeys.startAtLogin, defaultValues.startAtLogin)
    }


    override fun setHasCompletedFirstRun(completed: Boolean) {
        settings[OfflineStorageKeys.isFirstRun] = !completed
    }

    override fun setEnabled(enabled: Boolean) {
        settings[OfflineStorageKeys.enabled] = enabled
    }

    override fun setBreakFrequency(frequency: ZbTimeData) {
        settings.encodeValue(ZbTimeData.serializer(), OfflineStorageKeys.breakFrequency, frequency)
    }

    override fun setBreakDuration(duration: ZbTimeData) {
        settings.encodeValue(ZbTimeData.serializer(), OfflineStorageKeys.breakDuration, duration)
    }

    override fun setBreakSkip(skip: Boolean) {
        settings[OfflineStorageKeys.breakSkip] = skip
    }

    override fun setBreakSnooze(snooze: Boolean) {
        settings[OfflineStorageKeys.breakSnooze] = snooze
    }

    override fun setBreakSnoozeLength(snoozeLength: ZbTimeData) {
        settings.encodeValue(ZbTimeData.serializer(), OfflineStorageKeys.breakSnoozeLength, snoozeLength)
    }

    override fun setPopupNotification(popupNotification: Boolean) {
        settings[OfflineStorageKeys.popupNotification] = popupNotification
    }

    override fun setBreakMessage(message: String) {
        settings[OfflineStorageKeys.breakMessage] = message
    }

    override fun setPrimaryColor(primary: String) {
        settings[OfflineStorageKeys.primaryColor] = primary
    }

    override fun setSecondaryColor(secondary: String) {
        settings[OfflineStorageKeys.secondaryColor] = secondary
    }

    override fun setResetOnIdle(reset: Boolean) {
        settings[OfflineStorageKeys.resetOnIdle] = reset
    }

    override fun setStartAtLogin(start: Boolean) {
        settings[OfflineStorageKeys.startAtLogin] = start
    }
}