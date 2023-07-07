import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import data.repository.SettingsRepository
import ui.RootContent

class AppRoot {
    private val settingsRepository = SettingsRepository()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun App() = RootContent(settingsRepository)

    fun isFirstRun() = settingsRepository.isFirstRun.value

    fun firstRunCompleted() = settingsRepository.saveHasCompletedFirstRun(true)

    fun isEnabled() = settingsRepository.enabled.value

    fun toggleEnabled() {
        settingsRepository.saveEnabled(!settingsRepository.enabled.value)
    }
}