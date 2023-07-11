import androidx.compose.ui.window.ComposeUIViewController
import data.repository.SettingsRepository
import ui.ZenBreakUi

fun MainViewController(
    settingsRepository: SettingsRepository
) = ComposeUIViewController {
    ZenBreakUi(settingsRepository)
}