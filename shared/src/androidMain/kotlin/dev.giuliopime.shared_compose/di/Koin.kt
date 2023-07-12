package dev.giuliopime.shared_compose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import dev.giuliopime.shared_compose.data.repository.SettingsRepository
import dev.giuliopime.shared_compose.data.repository.impl.DefaultSettingsRepository
import dev.giuliopime.shared_compose.data.source.SettingsStorage
import dev.giuliopime.shared_compose.data.source.local.OfflineSettingsStorage
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@OptIn(ExperimentalSettingsApi::class)
actual fun platformModule() = module {
    single<FlowSettings> { createFlowSettings(get()) }
}

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
private fun createFlowSettings(context: Context): FlowSettings {
    return DataStoreSettings(context.dataStore)
}