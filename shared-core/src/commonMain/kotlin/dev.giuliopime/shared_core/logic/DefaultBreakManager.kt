package dev.giuliopime.shared_core.logic

import dev.giuliopime.shared_core.data.model.ZbSettings

expect class DefaultBreakManager(): IBreakManager

/**
 * Allows to plan and cancel a break.
 */
interface IBreakManager {
    /**
     * Sets the action of a break
     */
    fun setAction(breakAction: (ZbSettings) -> Unit)

    /**
     * Plans a break
     */
    fun planBreak(snoozed: Boolean = false)

    /**
     * Snoozes a break
     */
    fun snoozeBreak() = planBreak(true)

    /**
     * Cancels the planned break if it exists
     */
    fun cancelBreak()
}