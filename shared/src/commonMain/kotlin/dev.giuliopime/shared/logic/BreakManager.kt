package dev.giuliopime.shared.logic

import dev.giuliopime.shared.data.model.ZbSettings
import org.koin.core.component.KoinComponent

expect class BreakManager(): IBreakManager, KoinComponent

interface IBreakManager {
    fun setBreakAction(breakAction: (ZbSettings) -> Unit)
    /**
     * This will check for [ZbSettings.enabled] automatically before planning a break
     */
    fun planBreak()

    fun cancelBreak()
}