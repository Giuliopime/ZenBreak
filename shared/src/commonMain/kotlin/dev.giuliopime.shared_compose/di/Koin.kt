package dev.giuliopime.shared_compose.di

import dev.giuliopime.shared_compose.data.repository.SettingsRepository
import dev.giuliopime.shared_compose.data.repository.impl.DefaultSettingsRepository
import dev.giuliopime.shared_compose.data.source.SettingsStorage
import dev.giuliopime.shared_compose.data.source.local.OfflineSettingsStorage
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule, platformModule())
    }

val commonModule = module {
    single<SettingsStorage> { OfflineSettingsStorage() }
    single<SettingsRepository> { DefaultSettingsRepository() }
}