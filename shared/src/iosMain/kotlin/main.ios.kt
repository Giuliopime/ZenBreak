import androidx.compose.ui.window.ComposeUIViewController
import data.repository.SettingsRepository
import logic.BreakManager
import ui.ZenBreakUi

@Suppress("Unused", "FunctionName")
fun MainViewController(
    settingsRepository: SettingsRepository,
    breakManager: BreakManager
) = ComposeUIViewController {
    ZenBreakUi(
        breakManager = breakManager,
        settingsRepository = settingsRepository
    )
}