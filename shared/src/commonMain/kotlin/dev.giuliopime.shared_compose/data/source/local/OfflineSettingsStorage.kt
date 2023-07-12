package dev.giuliopime.shared_compose.data.source.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toBlockingSettings
import com.russhwolf.settings.set
import dev.giuliopime.shared_compose.core.decodeValue
import dev.giuliopime.shared_compose.core.encodeValue
import dev.giuliopime.shared_compose.core.decodeValueFlow
import dev.giuliopime.shared_compose.data.model.ZbSettings
import dev.giuliopime.shared_compose.data.model.ZbTimeData
import dev.giuliopime.shared_compose.data.source.SettingsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
class OfflineSettingsStorage: SettingsStorage, KoinComponent {
    private val settings: FlowSettings by inject()
    private val blockingSettings by lazy { settings.toBlockingSettings() }

    private val defaultValues = ZbSettings()

    override fun getZbSettingsFlow(): Flow<ZbSettings> {
        return settings.decodeValueFlow(OfflineStorageKeys.SETTINGS, defaultValues)
    }

    override fun getZbSettings(): ZbSettings = blockingSettings.decodeValue(OfflineStorageKeys.SETTINGS, defaultValues)

    override fun setHasCompletedFirstRun(completed: Boolean) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                hasCompletedFirstRun = completed
            )
        )
    }

    override fun setEnabled(enabled: Boolean) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                enabled = enabled
            )
        )
    }

    override fun setBreakFrequency(frequency: ZbTimeData) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                breakFrequency = frequency
            )
        )
    }

    override fun setBreakDuration(duration: ZbTimeData) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                breakDuration = duration
            )
        )
    }

    override fun setBreakSkip(skip: Boolean) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                breakSkip = skip
            )
        )
    }

    override fun setBreakSnooze(snooze: Boolean) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                breakSnooze = snooze
            )
        )
    }

    override fun setBreakSnoozeLength(snoozeLength: ZbTimeData) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                breakSnoozeLength = snoozeLength
            )
        )
    }

    override fun setPopupNotification(popupNotification: Boolean) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                popupNotification = popupNotification
            )
        )
    }

    override fun setBreakMessage(message: String) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                breakMessage = message
            )
        )
    }

    override fun setPrimaryColor(primary: String) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                primaryColor = primary
            )
        )
    }

    override fun setTextColor(text: String) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                textColor = text
            )
        )
    }

    override fun setResetOnIdle(reset: Boolean) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                resetOnIdle = reset
            )
        )
    }

    override fun setStartAtLogin(start: Boolean) {
        blockingSettings.encodeValue(
            OfflineStorageKeys.SETTINGS,
            getZbSettings().copy(
                startAtLogin = start
            )
        )
    }
}