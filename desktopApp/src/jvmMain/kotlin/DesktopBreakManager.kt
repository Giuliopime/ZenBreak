import dev.giuliopime.shared_compose.data.model.ZbSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import dev.giuliopime.shared_compose.logic.BreakManager
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

class DesktopBreakManager(
    private val breakNotification: () -> Unit
) : BreakManager {
    private var task: TimerTask? = null

    override fun planBreak(zbSettings: ZbSettings) {
        cancelBreak()

        if (zbSettings.enabled) {
            task = Timer().schedule(zbSettings.breakFrequency.millis) {
                println("BREAK RUNNING")
                runBlocking(Dispatchers.IO) {
                    breakNotification()
                }
            }

            println("Break planned for ${zbSettings.breakFrequency.millis}ms from now")
        }
    }

    override fun cancelBreak() {
        task?.cancel()?.let {
            if (it)
                println("BREAK CANCELED!")
        }
    }
}