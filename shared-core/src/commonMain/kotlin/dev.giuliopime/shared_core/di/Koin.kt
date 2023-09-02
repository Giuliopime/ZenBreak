package dev.giuliopime.shared_core.di

import dev.giuliopime.shared_core.data.repository.ISettingsRepository
import dev.giuliopime.shared_core.data.repository.impl.DefaultSettingsRepository
import dev.giuliopime.shared_core.data.source.SettingsStorage
import dev.giuliopime.shared_core.data.source.local.OfflineSettingsStorage
import dev.giuliopime.shared_core.logic.DefaultBreakManager
import dev.giuliopime.shared_core.logic.IBreakManager
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
import dev.giuliopime.shared_core.viewmodel.impl.DefaultZenBreakViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

/**
 * Initializes koin for dependency injection
 *
 * @param appDeclaration Application specific declaration
 */
fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule, platformModule())
    }

val commonModule = module {
    single<SettingsStorage> { OfflineSettingsStorage() }
    single<ISettingsRepository> { DefaultSettingsRepository() }
    single<IBreakManager> { DefaultBreakManager() }
    single<IZenBreakViewModel> { DefaultZenBreakViewModel() }
}