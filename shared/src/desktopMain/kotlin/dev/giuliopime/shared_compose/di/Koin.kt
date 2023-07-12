package dev.giuliopime.shared_compose.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import org.koin.dsl.module
import java.util.prefs.Preferences

@OptIn(ExperimentalSettingsApi::class)
actual fun platformModule() = module {
    single<FlowSettings> { PreferencesSettings(Preferences.userRoot()).toFlowSettings() }
}