plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.plugin.serialization)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget()

    // Windows and Linux targets
    jvm("desktop")

    // iOS
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // macOS
    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines)
                implementation(libs.kotlinx.serialization)
                implementation(libs.bundles.multiplatformSettings)
                implementation(libs.bundles.koin)
                // Needs to provide the apis to the modules that depend on this
                api(libs.koin.core)
            }
        }

        val desktopMain by getting {
            dependsOn(commonMain)
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.androidx.datastore.preferences)
                api(libs.multiplatform.settings.datastore)
                api(libs.koin.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val macosX64Main by getting
        val macosArm64Main by getting
        val macosMain by creating {
            dependsOn(commonMain)
            macosX64Main.dependsOn(this)
            macosArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "dev.giuliopime.zenbreak.shared"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    // This library doesn't have any resource

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
