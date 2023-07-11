import androidx.compose.runtime.Composable
import data.repository.SettingsRepository
import ui.ZenBreakUi

@Composable fun MainView(
    settingsRepository: SettingsRepository
) = ZenBreakUi(settingsRepository)
