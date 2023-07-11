import androidx.compose.runtime.Composable
import data.repository.SettingsRepository
import logic.BreakManager
import ui.ZenBreakUi

@Composable
fun MainView(
    settingsRepository: SettingsRepository,
    breakManager: BreakManager
) = ZenBreakUi(
    breakManager = breakManager,
    settingsRepository = settingsRepository
)