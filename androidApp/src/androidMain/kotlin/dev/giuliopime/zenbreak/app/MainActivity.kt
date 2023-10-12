package dev.giuliopime.zenbreak.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.giuliopime.shared_compose_settings.FeatureFlags
import dev.giuliopime.shared_core.viewmodel.IZenBreakViewModel
import dev.giuliopime.shared_compose_settings.ZenBreakUi
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: IZenBreakViewModel by inject()
    private val featureFlags = FeatureFlags(
        startAtLogin = false,
        resetOnIdle = false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ZenBreakUi(viewModel, featureFlags)
        }
    }
}