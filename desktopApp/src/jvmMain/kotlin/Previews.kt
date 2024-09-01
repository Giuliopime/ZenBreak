import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.giuliopime.shared_compose_settings.components.BooleanSetting
import dev.giuliopime.shared_compose_settings.components.ColorSetting
import dev.giuliopime.shared_compose_settings.components.DoubleChoiceSetting
import dev.giuliopime.shared_compose_settings.components.TimeInputSetting

@Preview
@Composable
fun BooleanSettingPreview() {
    var checked by remember {
        mutableStateOf(false)
    }

    /*
    TODO:
     - Change double choice setting to dropdown
     - Minute time input
     - quick actions in menu bar
     */

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BooleanSetting(
            checked,
            {
                checked = it
            },
            "Preview"
        )

        ColorSetting("Preview", "#FF00000", {})

        DoubleChoiceSetting("Preview", true, {}, "Ok", "not ok")

        TimeInputSetting(10000, {}, "Time")
    }
}