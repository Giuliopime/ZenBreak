package dev.giuliopime.shared_compose.di

import org.koin.dsl.module

actual fun platformModule() = module {
    single<FlowSettings> { NSUserDefaultsSettings(platform.Foundation.NSUserDefaults()).toFlowSettings() }
}