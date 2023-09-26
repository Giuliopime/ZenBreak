package dev.giuliopime.zenbreak.app

import android.app.Application
import dev.giuliopime.shared_core.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ZenBreakApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@ZenBreakApplication)
        }
    }
}
