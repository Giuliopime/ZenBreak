plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.plugin.serialization).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.native.coroutines).apply(false)
    alias(libs.plugins.native.cocoapods).apply(false)
    alias(libs.plugins.jvm).apply(false)
}
