import data.model.ZbSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import logic.BreakManager
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

class DesktopBreakManager(
    private val breakNotification: () -> Unit
) : BreakManager {
    private var task: TimerTask? = null

    override suspend fun planBreak(zbSettings: ZbSettings) {
        cancelBreak()
        if (zbSettings.enabled) {
            scheduleBreak(zbSettings)
            println("BREAK PLANNED for ${zbSettings.breakFrequency.millis}")
        }
    }

    private fun scheduleBreak(zbSettings: ZbSettings) {
        task = Timer().schedule(zbSettings.breakFrequency.millis) {
            println("BREAK RUNNING")
            runBlocking(Dispatchers.IO) {
                breakNotification()
            }
        }
    }

    override fun cancelBreak() {
        task?.cancel()
        println("BREAK CANCELED!")
    }
}