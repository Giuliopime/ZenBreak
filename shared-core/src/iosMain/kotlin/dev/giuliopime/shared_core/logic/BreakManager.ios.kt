package dev.giuliopime.shared_core.logic

import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.data.repository.ISettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSTimeInterval
import platform.Foundation.NSTimer

actual class DefaultBreakManager: IBreakManager, KoinComponent {
    private val settingsRepository: ISettingsRepository by inject()
    private var breakAction: (ZbSettings) -> Unit = {}
    private var timer: NSTimer? = null

    override fun setAction(breakAction: (ZbSettings) -> Unit) {
        this.breakAction = breakAction
    }

    override fun planBreak(snoozed: Boolean) {
        cancelBreak()

        val settings = settingsRepository.getSettings()

        if (!settings.enabled)
            return

        val delay = if (snoozed)
            settings.breakSnoozeLength
        else
            settings.breakFrequency

        timer = NSTimer.scheduledTimerWithTimeInterval(delay.div(1000).toDouble(), false) {
            println("Break running")
            breakAction(settings)
        }

        println("Break planned for ${delay}ms from now")
    }

    override fun cancelBreak() {
        timer?.invalidate()?.let {
            println("Break canceled")
        }

        timer = null
    }
}