val settingsVersion = "1.0.0"

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosX64()
    macosArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Business logic package for ZenBreak"
        homepage = "https://github.com/Giuliopime/ZenBreak"
        framework {
            baseName = "shared"
            isStatic = true
        }
        noPodspec()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("com.russhwolf:multiplatform-settings:$settingsVersion")
                implementation("com.russhwolf:multiplatform-settings-coroutines:$settingsVersion")
                implementation("com.russhwolf:multiplatform-settings-serialization:$settingsVersion")
                implementation("io.insert-koin:koin-core:3.4.2")
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("com.russhwolf:multiplatform-settings-datastore:1.0.0")
                api("androidx.datastore:datastore-preferences:1.0.0")
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

        val desktopMain by getting {
            dependsOn(commonMain)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "dev.giuliopime.zenbreak.common"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
}
