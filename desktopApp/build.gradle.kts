import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.conveyor)
}

group = "dev.giuliopime.zenbreak"
version = "1.0.1"

kotlin {

    jvm {
        withJava()
    }
    jvmToolchain(17)

    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                /*
                // https://github.com/gradle/gradle/issues/20274
                implementation("com.dorkbox:SystemTray:4.4") {
                    exclude("java.desktop")
                }
                 */
                implementation(compose.uiTooling)
                implementation(project(":shared-core"))
                implementation(project(":shared-compose-core"))
                implementation(project(":shared-compose-settings"))
                implementation(project(":shared-compose-popup"))
                api(libs.koin.core)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            vendor = "Hydraulic Software"
            description = "An app that reminds you to take a break from your screen!"
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}

configurations.all {
    attributes {
        attribute(Attribute.of("ui", String::class.java), "awt")
    }
}

dependencies {
    linuxAmd64(compose.desktop.linux_x64)
    windowsAmd64(compose.desktop.windows_x64)
}
