package dev.giuliopime.shared.di

import dev.giuliopime.shared.data.repository.SettingsRepository
import dev.giuliopime.shared.data.repository.impl.DefaultSettingsRepository
import dev.giuliopime.shared.data.source.SettingsStorage
import dev.giuliopime.shared.data.source.local.OfflineSettingsStorage
import dev.giuliopime.shared.logic.BreakManager
import dev.giuliopime.shared.logic.IBreakManager
import dev.giuliopime.shared.viewmodel.ZenBreakViewModel
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
    single<IBreakManager> { BreakManager() }
    single<ZenBreakViewModel> { ZenBreakViewModel() }
}