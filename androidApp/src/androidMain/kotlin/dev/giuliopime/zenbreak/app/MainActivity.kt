package dev.giuliopime.zenbreak.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.giuliopime.shared.viewmodel.ZenBreakViewModel
import dev.giuliopime.shared_compose.ZenBreakUi
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: ZenBreakViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ZenBreakUi(viewModel)
        }
    }
}