package dev.giuliopime.shared.logic

import dev.giuliopime.shared.data.model.ZbSettings
import dev.giuliopime.shared.data.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

actual class BreakManager: IBreakManager, KoinComponent {
    private val settingsRepository: SettingsRepository by inject()
    private var task: TimerTask? = null
    private var breakAction: (ZbSettings) -> Unit = {}

    override fun setBreakAction(breakAction: (ZbSettings) -> Unit) {
        this.breakAction = breakAction
    }

    override fun planBreak() {
        cancelBreak()

        val settings  = settingsRepository.getSettings()

        if (settings.enabled) {
            task = Timer().schedule(settings.breakFrequency.millis) {
                println("BREAK RUNNING")
                runBlocking(Dispatchers.IO) {
                    breakAction(settings)
                }
            }

            println("Break planned for ${settings.breakFrequency.millis}ms from now")
        }
    }

    override fun cancelBreak() {
        task?.cancel()?.let {
            if (it)
                println("BREAK CANCELED!")
        }
    }
}