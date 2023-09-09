package dev.giuliopime.shared_core.logic

import dev.giuliopime.shared_core.data.model.ZbSettings

actual class DefaultBreakManager: IBreakManager {
    override fun setAction(breakAction: (ZbSettings) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun planBreak(snoozed: Boolean) {
        TODO()
    }

    override fun cancelBreak() {
        TODO()
    }
}