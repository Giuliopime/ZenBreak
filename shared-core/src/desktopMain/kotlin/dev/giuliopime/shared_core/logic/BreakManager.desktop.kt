package dev.giuliopime.shared_core.logic

import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.data.repository.ISettingsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

actual class DefaultBreakManager: IBreakManager, KoinComponent {
    private val settingsRepository: ISettingsRepository by inject()
    private var breakTask: TimerTask? = null
    private var breakEndedTask: TimerTask? = null
    private var breakAction: (ZbSettings) -> Unit = {}
    private var breakEndedAction: (ZbSettings) -> Unit = {}

    override fun setAction(breakAction: (ZbSettings) -> Unit) {
        this.breakAction = breakAction
    }

    override fun setEndedAction(breakEndedAction: (ZbSettings) -> Unit) {
        this.breakEndedAction = breakEndedAction
    }

    override fun planBreak(snoozed: Boolean) {
        cancelBreak()

        val settings  = settingsRepository.getSettings()

        if (!settings.enabled)
            return

        val delay = if (snoozed)
            settings.breakSnoozeDuration
        else
            settings.breakFrequency

        breakTask = Timer().schedule(delay) {
            println("Break running")
            breakAction(settings)

            breakEndedTask = Timer().schedule(settings.breakDuration) {
                println("Break end action running")
                breakEndedAction(settings)
            }
        }

        println("Break planned for ${delay}ms from now")
    }

    override fun cancelBreak() {
        breakTask?.cancel()?.let {
            if (it)
                println("Break canceled")
        }

        breakEndedTask?.cancel()?.let {
            if (it)
                println("Break ended task canceled")
        }

        breakTask = null
        breakEndedTask = null
    }
}