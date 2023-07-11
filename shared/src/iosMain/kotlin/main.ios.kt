import androidx.compose.ui.window.ComposeUIViewController
import data.repository.SettingsRepository
import logic.BreakManager
import ui.ZenBreakUi

fun MainViewController(
    settingsRepository: SettingsRepository,
    breakManager: BreakManager
) = ComposeUIViewController {
    ZenBreakUi(
        breakManager = breakManager,
        settingsRepository = settingsRepository
    )
}