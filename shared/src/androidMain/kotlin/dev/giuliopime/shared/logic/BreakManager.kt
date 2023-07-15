package dev.giuliopime.shared.logic

import dev.giuliopime.shared.data.model.ZbSettings
import dev.giuliopime.shared.data.repository.SettingsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class BreakManager: IBreakManager, KoinComponent {
    private val settingsRepository: SettingsRepository by inject()
    private var breakAction: (ZbSettings) -> Unit = {}

    override fun setBreakAction(breakAction: (ZbSettings) -> Unit) {
        this.breakAction = breakAction
    }

    override fun planBreak() {
        TODO()
    }

    override fun cancelBreak() {
        TODO()
    }
}