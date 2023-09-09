package dev.giuliopime.shared_core.logic

import dev.giuliopime.shared_core.data.model.ZbSettings
import dev.giuliopime.shared_core.data.repository.ISettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

actual class DefaultBreakManager: IBreakManager, KoinComponent {
    private val settingsRepository: ISettingsRepository by inject()
    private var task: TimerTask? = null
    private var breakAction: (ZbSettings) -> Unit = {}

    override fun setAction(breakAction: (ZbSettings) -> Unit) {
        this.breakAction = breakAction
    }

    override fun planBreak(snoozed: Boolean) {
        cancelBreak()

        val settings  = settingsRepository.getSettings()

        if (!settings.enabled)
            return

        val delay = if (snoozed)
            settings.breakSnoozeLength
        else
            settings.breakFrequency

        task = Timer().schedule(delay) {
            println("Break running")
            breakAction(settings)
        }

        println("Break planned for ${delay}ms from now")
    }

    override fun cancelBreak() {
        task?.cancel()?.let {
            if (it)
                println("Break canceled")
        }

        task = null
    }
}