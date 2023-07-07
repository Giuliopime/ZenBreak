package data.repository

import androidx.compose.runtime.mutableStateOf
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.set
import data.model.TimeData
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
class SettingsRepository {
    private val settings = Settings()

    var isFirstRun = mutableStateOf(settings.getBoolean(Keys.isFirstRun, true))

    var enabled = mutableStateOf(settings.getBoolean(Keys.enabled, true))

    var breakFrequency = mutableStateOf(settings.decodeValueOrNull(
        serializer = TimeData.serializer(),
        key = Keys.breakFrequency
    ) ?: TimeData(0, 20, 0))

    var breakDuration = mutableStateOf(settings.decodeValueOrNull(
        serializer = TimeData.serializer(),
        key = Keys.breakDuration
    ) ?: TimeData(0, 2, 0))

    var breakSkip = mutableStateOf(settings.getBoolean(Keys.breakSkip, true))

    var breakSnooze = mutableStateOf(settings.getBoolean(Keys.breakSnooze, false))

    var breakSnoozeLength = mutableStateOf(settings.decodeValueOrNull(
        serializer = TimeData.serializer(),
        key = Keys.breakSnoozeLength
    ) ?: TimeData(0, 5, 0))

    var popupNotification = mutableStateOf(settings.getBoolean(Keys.popupNotification, true))

    var breakMessage = mutableStateOf(settings.getString(Keys.breakMessage, "It's time for a break!"))

    var primaryColor = mutableStateOf(settings.getString(Keys.primaryColor, "#343434"))

    var secondaryColor = mutableStateOf(settings.getString(Keys.secondaryColor, "#343434"))

    var resetOnIdle = mutableStateOf(settings.getBoolean(Keys.resetOnIdle, true))

    var startAtLogin = mutableStateOf(settings.getBoolean(Keys.startAtLogin, false))

    fun saveHasCompletedFirstRun(completed: Boolean) {
        settings[Keys.isFirstRun] = !completed
        isFirstRun.value = !completed
    }

    fun saveEnabled(enabled: Boolean) {
        settings[Keys.enabled] = enabled
        this.enabled.value = enabled
    }

    fun saveBreakFrequency(frequency: TimeData) {
        settings.encodeValue(TimeData.serializer(), Keys.breakFrequency, frequency)
        breakFrequency.value = frequency
    }

    fun saveBreakDuration(duration: TimeData) {
        settings.encodeValue(TimeData.serializer(), Keys.breakDuration, duration)
        breakDuration.value = duration
    }

    fun saveBreakSkip(skip: Boolean) {
        settings[Keys.breakSkip] = skip
        breakSkip.value = skip
    }

    fun saveBreakSnooze(snooze: Boolean) {
        settings[Keys.breakSnooze] = snooze
        breakSnooze.value = snooze
    }

    fun saveBreakSnoozeLength(snoozeLength: TimeData) {
        settings.encodeValue(TimeData.serializer(), Keys.breakSnoozeLength, snoozeLength)
        breakSnoozeLength.value = snoozeLength
    }

    fun savePopupNotification(popupNotification: Boolean) {
        settings[Keys.popupNotification] = popupNotification
        this.popupNotification.value = popupNotification
    }

    fun saveBreakMessage(message: String) {
        settings[Keys.breakMessage] = message
        breakMessage.value = message
    }

    fun savePrimaryColor(primary: String) {
        settings[Keys.primaryColor] = primary
        primaryColor.value = primary
    }

    fun saveSecondaryColor(secondary: String) {
        settings[Keys.secondaryColor] = secondary
        secondaryColor.value = secondary
    }

    fun saveResetOnIdle(reset: Boolean) {
        settings[Keys.resetOnIdle] = reset
        resetOnIdle.value = reset
    }

    fun saveStartAtLogin(start: Boolean) {
        settings[Keys.startAtLogin] = start
        startAtLogin.value = start
    }

    private object Keys {
        const val isFirstRun = "IS_FIRST_RUN"
        const val enabled = "ENABLED"
        const val breakFrequency = "BREAK_FREQUENCY"
        const val breakDuration = "BREAK_DURATION"
        const val breakSkip = "BREAK_SKIP"
        const val breakSnooze = "BREAK_SNOOZE"
        const val breakSnoozeLength = "BREAK_SNOOZE_LENGTH"
        const val popupNotification = "POPUP_NOTIFICATION"
        const val breakMessage = "BREAK_MESSAGE"
        const val primaryColor = "PRIMARY_COLOR"
        const val secondaryColor = "SECONDARY_COLOR"
        const val resetOnIdle = "RESET_ON_IDLE"
        const val startAtLogin = "START_AT_LOGIN"
    }
}