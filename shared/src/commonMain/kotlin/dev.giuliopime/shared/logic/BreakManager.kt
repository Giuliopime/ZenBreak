package dev.giuliopime.shared.logic

import dev.giuliopime.shared.data.model.ZbSettings

interface BreakManager {
    /**
     * This will check for [ZbSettings.enabled] automatically before planning a break
     */
    fun planBreak(zbSettings: ZbSettings)

    fun cancelBreak()
}