package dev.giuliopime.shared_core.logic

import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.data.repository.ISettingsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSTimer

actual class DefaultBreakManager: IBreakManager, KoinComponent {
    private val settingsRepository: ISettingsRepository by inject()
    private var breakAction: (ZbSettings) -> Unit = {}
    private var breakEndedAction: (ZbSettings) -> Unit = {}
    private var breakTimer: NSTimer? = null
    private var breakEndedTimer: NSTimer? = null

    override fun setAction(breakAction: (ZbSettings) -> Unit) {
        this.breakAction = breakAction
    }

    override fun setEndedAction(breakEndedAction: (ZbSettings) -> Unit) {
        this.breakEndedAction = breakEndedAction
    }

    override fun planBreak(snoozed: Boolean) {
        cancelBreak()

        val settings = settingsRepository.getSettings()

        if (!settings.enabled)
            return

        val delay = if (snoozed)
            settings.breakSnoozeDuration
        else
            settings.breakFrequency

        breakTimer = NSTimer.scheduledTimerWithTimeInterval(delay.div(1000).toDouble(), false) {
            println("Break running")
            breakAction(settings)

            breakEndedTimer = NSTimer.scheduledTimerWithTimeInterval(settings.breakDuration.div(1000).toDouble(), false) {
                println("Break end action running")
                breakEndedAction(settings)
            }
        }

        println("Break planned for ${delay}ms from now")
    }

    override fun cancelBreak() {
        breakTimer?.invalidate()?.let {
            println("Break canceled")
        }

        breakEndedTimer?.invalidate()?.let {
            println("Break end canceled")
        }

        breakTimer = null
        breakEndedTimer = null
    }
}